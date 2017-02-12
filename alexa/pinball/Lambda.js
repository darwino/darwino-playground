'use strict';

/**
 * Darwino Playground Pinball
 */


// --------------- Helpers that build all of the responses -----------------------

function buildSpeechletResponse(title, output, repromptText, shouldEndSession) {
    return {
        outputSpeech: {
            type: 'PlainText',
            text: output,
        },
        card: {
            type: 'Simple',
            title: `SessionSpeechlet - ${title}`,
            content: `SessionSpeechlet - ${output}`,
        },
        reprompt: {
            outputSpeech: {
                type: 'PlainText',
                text: repromptText,
            },
        },
        shouldEndSession,
    };
}

function buildResponse(sessionAttributes, speechletResponse) {
    return {
        version: '1.0',
        sessionAttributes,
        response: speechletResponse,
    };
}


// --------------- Helpers for calling the Darwino playground -----------------------

function callDarwino(query, params, callback, error) {
    //var http = require('http');
    var http = require('https');    
    var url = "https://playground.darwino.com/playground.nsf/playground/$darwino-jstore/databases/playground/jsql/queries/"
                + encodeURIComponent("darwino/alexa/"+query);
    if(params) {
        let p = JSON.stringify(params);
        url += '?parameters='+encodeURIComponent(p);
    }
    console.log(url);
    http.get(url, function (res) {
        let result = '';
        console.log('Status Code: ' + res.statusCode);
 
        if (res.statusCode != 200) {
            if(error) {
                let errmsg = `I got an error from Darwino, status code `+res.statusCode;
                error(errmsg)
            }
        }
 
        res.on('data', function (data) {
            result += data;
        });
 
        res.on('end', function () {
            var json = JSON.parse(result);
            if (json.errorId) {
                console.log('Error: ' + result);
                if(error) {
                    let errmsg = `I got a response error from Darwino.`;
                    error(errmsg,json)
                }
            } else {
                if(callback) {
                    callback(json)
                }
            }
        });
    }).on('error', function (e) {
        console.log("Communications error: " + e.message);
        error(e.msg)
    });
}


// --------------- Calling a generic Darwino micro service -----------------------

// Add a space before every capital letter (acrobyms are preserved)
// http://stackoverflow.com/questions/5582228/insert-space-before-capital-letters
function serviceName(s) {
    s = s.replace(/([a-z])([A-Z])/g, '$1 $2');
    s = s.replace(/([A-Z])([A-Z][a-z])/g, '$1 $2')
    return s;
}

function callDarwinoService(service, _intent, _session, _callback) {
    var sessionAttributes={};
    
    var svc = service.trim();
    if(svc.endsWith("Intent")) {
        svc = svc.slice(0,-("Intent".length))
    }
    svc = "darwino/alexa/"+serviceName(svc.trim())+".groovy";
    
    //var http = require('http');
    var http = require('https');    

    var type = 'intent'

    var params = {}
    for(let k in _intent.slots) {
        params[k] = _intent.slots[k].value;
    }

    var postData = JSON.stringify({
        type: type,
        params: params,
        session: sessionAttributes
    });

    var options = {
        hostname: 'playground.darwino.com',
        port: 443,
        path: '/playground.nsf/playground/$darwino-services/'+ encodeURIComponent(svc),
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Content-Length': Buffer.byteLength(postData)
        }
    };
    
    var req = http.request(options, function(res) {
        if(res.statusCode!=200) {
            console.log("Request Error: "+res.statusCode);
            console.log("options: "+JSON.stringify(options));
            _callback(sessionAttributes,
                buildSpeechletResponse(_intent.name, 'I got an error from Darwino, status code '+res.statusCode, null, true))  
            return;
        }
        
        let result = '';
        res.setEncoding('utf8');
        res.on('data', function(data) {
            result += data;
        });
        res.on('end', function () {
            var json = JSON.parse(result);
            var speech = json.speech;
            var reprompt = json.reprompt;
            var keepSession = json.keepSession;
            console.log("Request data: "+JSON.stringify(json));
            _callback(sessionAttributes,
                buildSpeechletResponse(_intent.name, speech, reprompt, !keepSession))  
        });    
    });
    req.on('error', function(e) {
        _callback(sessionAttributes,
            buildSpeechletResponse(_intent.name, `I got a problem with the request`, null, true))  
        console.log('problem with request: ' + e.message);
    });
    
    req.write(postData);
    req.end();    
}


// --------------- Functions that control the skill's behavior -----------------------

function getWelcomeResponse(callback) {
    // If we wanted to initialize the session to have some attributes we could add those here.
    const sessionAttributes = {};
    const cardTitle = 'Welcome to the Pinball database';
    const speechOutput = 'Welcome to the Darwino pinball database.';
    const repromptText = 'Please ask me a question about pinballs, like: how many pinballs do you know about?';
    const shouldEndSession = false;

    callback(sessionAttributes,
        buildSpeechletResponse(cardTitle, speechOutput, repromptText, shouldEndSession));
}

function handleSessionEndRequest(callback) {
    const cardTitle = 'PinballSession Ended';
    const speechOutput = 'Thank you for trying the Alexa Skills Kit sample. Have a nice day!';
    // Setting this to true ends the session and exits the skill.
    const shouldEndSession = true;

    callback({}, buildSpeechletResponse(cardTitle, speechOutput, null, shouldEndSession));
}


// --------------- Events -----------------------

/**
 * Called when the session starts.
 */
function onSessionStarted(sessionStartedRequest, session) {
    console.log(`onSessionStarted requestId=${sessionStartedRequest.requestId}, sessionId=${session.sessionId}`);
}

/**
 * Called when the user launches the skill without specifying what they want.
 */
function onLaunch(launchRequest, session, callback) {
    console.log(`onLaunch requestId=${launchRequest.requestId}, sessionId=${session.sessionId}`);

    // Dispatch to your skill's launch.
    getWelcomeResponse(callback);
}

/**
 * Called when the user specifies an intent for this skill.
 */
function onIntent(intentRequest, session, callback) {
    console.log(`onIntent requestId=${intentRequest.requestId}, sessionId=${session.sessionId}`);

    const intent = intentRequest.intent;
    const intentName = intentRequest.intent.name;

    // Dispatch to your skill's intent handlers
     if (intentName === 'AMAZON.HelpIntent') {
        getWelcomeResponse(callback);
    } else if (intentName === 'AMAZON.StopIntent' || intentName === 'AMAZON.CancelIntent') {
        handleSessionEndRequest(callback);
    }
    
    // Darwino dynamic services, for unknown static ones
    else {
        callDarwinoService(intentName, intent, session, callback);
    }
}


/**
 * Called when the user ends the session.
 * Is not called when the skill returns shouldEndSession=true.
 */
function onSessionEnded(sessionEndedRequest, session) {
    console.log(`onSessionEnded requestId=${sessionEndedRequest.requestId}, sessionId=${session.sessionId}`);
    // Add cleanup logic here
}


// --------------- Main handler -----------------------

// Route the incoming request based on type (LaunchRequest, IntentRequest,
// etc.) The JSON body of the request is provided in the event parameter.
exports.handler = (event, context, callback) => {
    try {
        console.log(`event.session.application.applicationId=${event.session.application.applicationId}`);

        /**
         * Uncomment this if statement and populate with your skill's application ID to
         * prevent someone else from configuring a skill that sends requests to this function.
         */
        /*
        if (event.session.application.applicationId !== 'amzn1.echo-sdk-ams.app.[unique-value-here]') {
             callback('Invalid Application ID');
        }
        */

        if (event.session.new) {
            onSessionStarted({ requestId: event.request.requestId }, event.session);
        }

        if (event.request.type === 'LaunchRequest') {
            onLaunch(event.request,
                event.session,
                (sessionAttributes, speechletResponse) => {
                    callback(null, buildResponse(sessionAttributes, speechletResponse));
                });
        } else if (event.request.type === 'IntentRequest') {
            onIntent(event.request,
                event.session,
                (sessionAttributes, speechletResponse) => {
                    callback(null, buildResponse(sessionAttributes, speechletResponse));
                });
        } else if (event.request.type === 'SessionEndedRequest') {
            onSessionEnded(event.request, event.session);
            callback();
        }
    } catch (err) {
        callback(err);
    }
};

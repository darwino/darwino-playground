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

function getPinballBrandCount(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;
    let brand = intent.slots.brand.value;
    callDarwino("How Many Pinballs Are Made By", {brand:brand},
        function(json) {
            let count = json[0].count;
            let speechOutput = '';
            if(count>0) {
                speechOutput = `${brand} is currently making ${count} pinball machines.`;
            } else {
                speechOutput = `I don't know any pinball machine made by ${brand}`;
            }
            console.log("speechOutput:"+speechOutput)
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
}

function getPinballCount(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;

    callDarwino("How Many Pinballs Do You Know", null,
        function(json) {
            var pinballCount = json[0].count
            let speechOutput = `I currently know about ${pinballCount} pinball machines.`;
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
}

function getBrands(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;

    callDarwino("What Are The Brands", null,
        function(json) {
            let br = "";
            for(let i=0; i<json.length; i++) {
                if(i>0) { br += i==json.length-1 ? ' and ' : ','; }
                br+=json[i].brand
            }
            let speechOutput = `The brands I know about are ${br}.`;
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
}

function getMostExpensivePinball(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;

    callDarwino("What Is The Most Expensive Pinball", null,
        function(json) {
            var manufacturer = json[0].manufacturer
            var name = json[0].name
            var price = json[0].value
            let speechOutput = `The most expensive pinball machine is ${name} by ${manufacturer}. It costs ${price} dollars.`;
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
}

function getManufacturer(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;
    let brand = intent.slots.brand.value;
    callDarwino("Which Pinballs Are Made By", {brand:brand},
        function(json) {
            let count = json.length;
            let speechOutput = '';
            if(count>0) {
                speechOutput = `${brand} is currently making ${count} pinball machines, like`;
                for(let i=0; i<Math.min(3,json.length); i++) {
                    speechOutput+=json[i].name +','
                }
                if(json.length>3) {
                    speechOutput += " and more..."
                }
            } else {
                speechOutput = `I don't know any pinball machine made by ${brand}`;
            }
            console.log("speechOutput:"+speechOutput)
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
}

function getWhoMakes(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;
    let model = intent.slots.model.value;
    callDarwino("Who Makes", {model:model},
        function(json) {
            let count = json.length;
            let speechOutput = '';
            if(count>0) {
                let brand = json[0].brand;
                speechOutput = `${model} is made by ${brand}.`;
            } else {
                speechOutput = `I don't know any pinball machine named ${model}`;
            }
            console.log("speechOutput:"+speechOutput)
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
}

function getWhoOwns(intent, session, callback) {
    const sessionAttributes = {};    
    const repromptText = null;
    let shouldEndSession = true;
    let model = intent.slots.model.value;
    callDarwino("Who Owns", {model:model},
        function(json) {
            let count = json.length;
            let speechOutput = '';
            if(count>0) {
                speechOutput = `${model} is owned by `;
                for(let i=0; i<Math.min(3,json.length); i++) {
                    if(i>0) { speechOutput += i==json.length-1 ? ' and ' : ','; }
                    speechOutput+=json[i].owner +','
                }
                if(json.length>3) {
                    speechOutput += " and more..."
                }
            } else {
                speechOutput = `I don't know any pinball machine named ${model}`;
            }
            console.log("speechOutput:"+speechOutput)
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, speechOutput, repromptText, shouldEndSession))    
        }, function(msg) {
            callback(sessionAttributes,
                buildSpeechletResponse(intent.name, msg, repromptText, shouldEndSession))    
        }
    )    
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

    // Darwino skills
    if (intentName === 'PinballBrandCountIntent') {
        getPinballBrandCount(intent, session, callback);
    } else if (intentName === 'PinballCountIntent') {
        getPinballCount(intent, session, callback);
    } else if (intentName === 'AllBrandsIntent') {
        getBrands(intent, session, callback);
    } else if (intentName === 'MostExpensivePinballIntent') {
        getMostExpensivePinball(intent, session, callback);
    } else if (intentName === 'WhatAreMadeIntent') {
        getManufacturer(intent, session, callback);
    } else if (intentName === 'WhoMakesIntent') {
        getWhoMakes(intent, session, callback);
    } else if (intentName === 'WhoOwnsIntent') {
        getWhoOwns(intent, session, callback);
    }
    
    // Dispatch to your skill's intent handlers
    else if (intentName === 'AMAZON.HelpIntent') {
        getWelcomeResponse(callback);
    } else if (intentName === 'AMAZON.StopIntent' || intentName === 'AMAZON.CancelIntent') {
        handleSessionEndRequest(callback);
    } else {
        throw new Error('Invalid intent');
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

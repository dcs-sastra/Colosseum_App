const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.createUser = functions.firestore
    .document('current_events/{id}')
    .onCreate((snap, context) => {
      // Get an object representing the document
      // e.g. {'name': 'Marie', 'age': 66}
      const newValue = snap.data();
      console.log("DOCUMENT ADDED");
      // access a particular field as you would any JS property
      var gameName = newValue.event_name;
      var isNext = newValue.nextTime;
      var time = newValue.event_time;
      
      if(isNext){
      	gameName = gameName+" event is to be ";
      	time = "Time will be updated";
      }else{
      	gameName = gameName+" event is to be  conducted on ";
      }

      // perform desired operations ...
      const payload = {
        notification: {
            title:gameName,
            body: time,
            sound: "default"
		},
	  };

	  const options = {
        priority: "high",
        timeToLive: 60 * 60 * 24
    };


	return admin.messaging().sendToTopic("FIXTURE_CHANNEL", payload, options);
    });

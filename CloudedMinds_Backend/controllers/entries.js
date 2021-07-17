var bodyParser = require("body-parser");
var path = require('path');
const Entry = require('../models/entries');

exports.insert = function (req, res) {
    let userData = req.body;

    if(userData == null){
        res.status(403).send('No data sent!')
    }

    try {
        let entry = new Entry({
            event: userData.event,
            date: userData.date,
            mood: userData.mood,
            mood_rating: userData.mood_rating,
            catastrophise: userData.catastrophise,
            generalise: userData.generalise,
            ignoring: userData.ignoring,
            self_critical: userData.self_critical,
            mind_reading: userData.mind_reading,
            changed_mood: userData.changed_mood,
            changed_rating: userData.changed_rating
        });

        console.log('received: ' + entry);

        entry.save(function (err) {
            if (err) {
                console.log(err.stack);
                res.status(500).send('Invalid data!');
            } else {
                res.redirect('back');
            }
        });
    } catch (e) {
        res.status(500).send('error ' + e);
    }
}

exports.listData = function (req, res) {
    Entry.find({}, 'event date mood mood_rating catastrophise generalise ignoring self_critical mind_reading changed_mood changed_rating', function (err, entries) {
        if (err) {
            return res.send(500, err);
        }
        res.render('index', {
            data: entries
        });
    });
}
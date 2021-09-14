var bodyParser = require("body-parser");
var path = require('path');
const Entry = require('../models/entries');
/**
 * Saves the user entries from 'Catch It, Check It, Change It' session to the
 * database. The Entries are outputted into the console if successful. This throws
 * a Forbidden error if the user has not entered any data and also throws an
 * Internal Server Error if the user entered data in invalid.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.insert = function (req, res) {
    let userData = req.body;

    if(userData == null){
        res.status(403).send('No data sent!')
    }

    try {
        let entry = new Entry({
            user: userData.user,
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

/**
 * Displays the user entries that are saved in the database in the index page of the
 * CloudedMinds website. Throws an Internal Server Error if there are issues retrieving
 * data.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.listData = function (req, res) {
    Entry.find({}, 'user event date mood mood_rating catastrophise generalise ignoring self_critical mind_reading changed_mood changed_rating', function (err, entries) {
        if (err) {
            return res.status(500).send(err);
        }
        res.render('index', {
            data: entries
        });
    });
}
/**
 * Displays the user entries saved in the database as JSON values. This helps in
 * displaying the user entries in the 'Diary' screen of the CloudedMinds app. It
 * throws an Internal Server Error if there are any issues retrieving data.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.listJsonData = function (req, res) {
    Entry.find({}, 'user event date mood mood_rating catastrophise generalise ignoring self_critical mind_reading changed_mood changed_rating', function (err, values) {
        if (err) {
            return res.send(500, err);
        }

        res.json(values);
    });
}

/**
 * Deletes a specific entry from the database when the medical professional chooses.
 * Throws an Internal Server Error if there are any issues retrieving data.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.delete = function (req, res) {
    Entry.remove({_id: req.params.id}, function (err){
        if (err){
            res.status(500).send(err);
        }
        else{
            res.redirect('/');
        }
    });
}

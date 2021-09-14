var bodyParser = require("body-parser");
var path = require('path');
const Patient = require('../models/patients');
var bcrypt = require('bcryptjs');

/**
 * Saves a new user account created in the CloudedMinds app into the
 * database. It checks whether the username is unique and if not, sends
 * a "Username already exists" response. It also encrypts the user entered
 * password. Provides a Forbidden error if the user does not enter the
 * required details and throws an Internal Server Error if the details
 * are invalid.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.insertPatient = function (req, res) {
    let userData = req.body;

    if(userData == null){
        res.status(403).send('No data sent!')
    }


    Patient.find({'username': userData.username}).count(function (err, number){
        if(number != 0)
        {
            res.json('Username already exists');
            console.log('Username already exists');
        }
        else{
            try {
                let patient = new Patient({
                    name: userData.name,
                    username: userData.username,
                    password: bcrypt.hashSync(userData.password, 10)
                });

                console.log('received: ' + patient);

                patient.save(function (err) {
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
    })
}

/**
 * Displays the user accounts on the 'Patients' page of the CloudedMinds website.
 * Throws an Interal Server Error if there are any issues retrieving the data.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.listPatients = function (req, res) {
    Patient.find({}, 'name username password', function (err, patient) {
        if (err) {
            return res.send(500, err);
        }
        res.render('patients', {
            data: patient
        });
    });
}

/**
 * Logs in a user into the CloudedMinds app. Checks if the username exists in the
 * username, if not, sends a "Username does not exist" response to the server.
 * If username exists, the password entered is checked against the encrypted
 * password to see if they match. If they do, a "Login Successful" response is
 * sent to the server, otherwise a "Password is incorrect" response is sent.
 *
 * @param req The HTTP request object
 * @param res The HTTP response object
 */
exports.loginPatient = function (req, res) {
    let userData = req.body;

    if(userData == null){
        res.status(403).send('No data sent!')
    }

    Patient.find({'username': userData.username}).count(function (err,number){
        if (number == 0)
        {
            res.json('Username does not exist');
            console.log('Username does not exist');
        }
        else{
            Patient.findOne({'username': userData.username}, function (err, patient) {
                var passwordIsValid = bcrypt.compareSync(
                    userData.password,
                    patient.password
                );

                if (passwordIsValid) {
                    res.json('Login Successful');
                    console.log('Login Successful');
                }
                else {
                    res.json('Password is incorrect');
                    console.log('Password is incorrect');
                }
            })
        }
    })
}
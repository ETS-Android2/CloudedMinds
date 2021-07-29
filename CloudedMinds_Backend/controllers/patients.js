var bodyParser = require("body-parser");
var path = require('path');
const Patient = require('../models/patients');
var bcrypt = require('bcryptjs');

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
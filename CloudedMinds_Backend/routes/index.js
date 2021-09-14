var express = require('express');
var router = express.Router();
var bodyParser = require("body-parser");
const entry = require('../controllers/entries');
const patient = require('../controllers/patients');

var entries = require('../models/entries');

/* GET home page. */
router.get('/', function(req, res, next) {
  entry.listData(req,res);
});

/* GET home page. */
router.get('/entries', function(req, res, next) {
  entry.listJsonData(req,res);
});

/* POST home page */
router.post('/', function (req, res){

  entry.insert(req, res);
})

/* GET delete entry */
router.get('/delete/:id', function (req, res){

  entry.delete(req, res);
})

/* GET patients page. */
router.get('/patients', function(req, res, next) {
  patient.listPatients(req,res);
});

/* POST register patients */
router.post('/patients', function (req, res){

  patient.insertPatient(req, res);
})

/* POST Login patient */
router.post('/login', function (req, res){

  patient.loginPatient(req, res);
})

module.exports = router;

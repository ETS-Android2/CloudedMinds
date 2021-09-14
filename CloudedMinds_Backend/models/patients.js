// Patients Model
let mongoose = require('mongoose');
let Schema = mongoose.Schema;

let Patient = new Schema(
    {
        name: {type: String, required: true},
        username: {type: String, unique: true, required: true},
        password: {type: String, required: true}
    }
);

let patientModel = mongoose.model('Patient', Patient);

module.exports = patientModel;


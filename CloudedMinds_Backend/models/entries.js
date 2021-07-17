let mongoose = require('mongoose');
let Schema = mongoose.Schema;

let Entry = new Schema(
    {
        event: {type: String},
        date: {type: String},
        mood: {type: String},
        mood_rating: {type: Number},
        catastrophise: {type: String},
        generalise: {type: String},
        ignoring: {type: String},
        self_critical: {type: String},
        mind_reading: {type: String},
        changed_mood: {type: String},
        changed_rating: {type: Number}
    }
);

let entryModel = mongoose.model('Entry', Entry);

module.exports = entryModel;


let mongoose = require('mongoose');
let Schema = mongoose.Schema;

let Entry = new Schema(
    {
        user: {type: String, required: true},
        event: {type: String, required: true},
        date: {type: String, required: true},
        mood: {type: String, required: true},
        mood_rating: {type: Number, required: true},
        catastrophise: {type: String, required: true},
        generalise: {type: String, required: true},
        ignoring: {type: String, required: true},
        self_critical: {type: String, required: true},
        mind_reading: {type: String, required: true},
        changed_mood: {type: String, required: true},
        changed_rating: {type: Number, required: true}
    }
);

let entryModel = mongoose.model('Entry', Entry);

module.exports = entryModel;


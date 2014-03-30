from flask_wtf import Form 
from wtforms import TextField
from wtforms.validators import DataRequired

class SubscribeForm(Form):
	name = TextField('Name', [validators.Length(min=1, max=50)])
	phone_number = TextField('Phone Number', [validators.Length(min=10, max=11)])

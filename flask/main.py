from flask import Flask, request, jsonify, session, render_template, Response, url_for

from twilio_wrapper import sms_message, phone_call

app = Flask(__name__)
app.config.from_object(__name__)

PHONE_NUMBERS = [
	'+13013570811',
	'+14085156785'
]

@app.route('/sendtest', methods=['GET', 'POST'])
def send_test():
    if request.method == 'POST':
        name = request.json['name']
        sms_message.send(name, to_number='13013570811')
        return name if name else "Error"


@app.route('/add-user', methods=['GET', 'POST'])
def add_user():
	return ''


@app.route('/text', methods=['GET', 'POST'])
def send_sms():
	if request.method == 'POST':
		message = request.json['message']
		for num in PHONE_NUMBERS:
			sms_message.send(message, num)
		return 'Success'

@app.route('/call', methods=['GET', 'POST'])
def call_number():
	if request.method == 'POST':
		message = request.json['message']
		with app.test_request_context():
			url = url_for('xml', msg=message, _external=True)
			print url
		for num in PHONE_NUMBERS:
			phone_call.call(url, num)

@app.route('/gen-xml', methods=['GET'])
def xml():
	message = request.args.get('msg')
	return Response(
	"""<response>
		<say>%s</say>
	</response>""" % message, mimetype='text/xml')


@app.route('/', methods=['GET', 'POST'])
def home():
    return render_template("index.html")

if __name__ == '__main__':
	app.run(host="0.0.0.0", debug=True)

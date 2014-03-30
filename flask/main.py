from flask import Flask, request, jsonify, render_template, Response, url_for
from twilio_settings import *
import urllib2, os

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
			twilio_send(message, num)
		return 'Success'

@app.route('/call', methods=['GET', 'POST'])
def call_number():
	if request.method == 'POST':
		message = request.json['message']

		print 'request.path: ' + request.path
		print 'request.script_root: ' + request.script_root
		print 'request.base_url: ' + request.base_url
		print 'request.url: ' + request.url
		print 'request.url_root: ' + request.url_root
		with app.test_request_context():
			url = 'http://triocast.herokuapp.com' + url_for('xml', msg=message)
			# url = url_for('xml', msg=message, _external=True)
			print url

			for num in PHONE_NUMBERS:
				print 'CALLING: %s' % num
				twilio_call(url, num)

		return "Call succeeded"

def twilio_call(url, to_number, from_number = SENDER):
	call = client.calls.create(to_number, from_number, url)

def twilio_send(text, to_number, from_number = SENDER):
	message = client.sms.messages.create(
		body = text,
		to = to_number,
		from_ = from_number
	)

@app.route('/gen-xml', methods=['GET', 'POST'])
def xml():
	message = request.args.get('msg')
	return Response(
	"""<Response>
		<Say>%s</Say>
	</Response>""" % message, mimetype='text/xml')


@app.route('/', methods=['GET', 'POST'])
def home():
    return render_template("index.html")

if __name__ == '__main__':
	print 'hi'
	port = int(os.environ.get("PORT", 5000))
	app.run(host="0.0.0.0", port=port)
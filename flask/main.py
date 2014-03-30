from flask import Flask, request, jsonify, render_template, Response, url_for
from twilio_settings import *
import urllib2, os

import data_manager

app = Flask(__name__)
app.config.from_object(__name__)

@app.route('/add-person', methods=['GET', 'POST'])
def add_person():
	print 'adding someone'
	if request.method == 'POST':
		name = request.json['name']
		number = request.json['number']
		sms = request.json['sms']
		call = request.json['call']
		print name, number, sms, call

		data_manager.add(name, number, sms, call)
		print 'Added'
	return render_template("index.html")

@app.route('/people')
def list_people():
	print 'people'
	foo = ['Peggy']
	print foo
	return render_template("people.html")


@app.route('/text', methods=['GET', 'POST'])
def send_sms():
	people = data_manager.read()
	if request.method == 'POST':
		message = request.json['message']
		for person in people:
			info = person.split(',')
			if info[2] == 'True':
				num = info[1]
				twilio_send(message, num)
		return 'Success'

@app.route('/call', methods=['GET', 'POST'])
def call_number():
	if request.method == 'POST':
		message = request.json['message']

		with app.test_request_context():
			url = 'http://triocast.herokuapp.com' + url_for('xml', msg=message)
			if (url):
				people = data_manager.read()
				for person in people:
					info = person.split(',')
					if info[3] == 'True':
						num = info[1]
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
	port = int(os.environ.get("PORT", 5000))
	app.run(host="0.0.0.0", port=port)
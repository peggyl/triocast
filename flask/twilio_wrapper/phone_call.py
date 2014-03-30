from twilio.rest import TwilioRestClient

from twilio_settings import *

import hashlib, os

XML_DIRECTORY = 'xmldump'

def call(message, to_number, from_number = SENDER):
	"""
	Make a call with a voice reading the message (string)
	"""
	fname = create_say_xml(message)
	print to_number, SENDER, fname
	print client.calls
	call = client.calls.create(to_number, SENDER, fname)

	print call.sid

def create_say_xml(message):
	fname = "https://www.dropbox.com/s/3nsxv9frx3cih31/63325070d56c3580500f5f64d0b1e27bb3b68961f166959fe51369a1.xml"
	# with open(fname, 'w') as f:
	# 	f.write('<Response>')
	# 	f.write('\t<Say>%s</Say>' % message)
	# 	f.write('</Response>')
	return fname


def get_hash(message):
	return hashlib.sha224(message).hexdigest()


if __name__ == '__main__':
	call("Hello Peggy", "+13013570811")
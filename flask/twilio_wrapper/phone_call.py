from twilio.rest import TwilioRestClient

from twilio_settings import *

import hashlib, urllib2

ROOT = ''

def call(url, to_number, from_number = SENDER):
	"""
	Make a call with a voice reading the message at url
	"""
	
	call = client.calls.create(to_number, from_number, url)

if __name__ == '__main__':
	call("Hello Peggy", "+13013570811")
from twilio_settings import *

def send(text, to_number, from_number = SENDER):
	"""
	Send a SMS message with content text
	"""
	message = client.sms.messages.create(
		body = text,
		to = to_number,
		from_ = from_number
	)

if __name__ == '__main__':
	send("Hello Peggy", "13013570811")
from twilio.rest import TwilioRestClient

account_sid = "AC25ddbadc25e9d97f7a2f0610441e68ae"
auth_token	= "5f2d3d3f762a2f7675a1215849e44fe9"
client 		= TwilioRestClient(account_sid, auth_token)

SENDER = '12408216901'

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
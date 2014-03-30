from flask import Flask, request, jsonify, session, render_template

from twilio_wrapper import sms_message

app = Flask(__name__)
app.config.from_object(__name__)

@app.route('/sendtest', methods=['GET', 'POST'])
def send_test():
    if request.method == 'POST':
        name = request.json['name']
        sms_message.send(name, to_number='13013570811')
        return name if name else "Error"


@app.route('/', methods=['GET', 'POST'])
def home():
    return render_template("index.html")

@app.route('/xmldump', methods=['GET', 'POST'])    
# @app.route('/xmldump/<xfile>')
def xml():
	return "hello"

if __name__ == '__main__':
	app.run(host="0.0.0.0", debug=True)

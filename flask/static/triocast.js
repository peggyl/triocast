$(document).ready(function() {
	$("#datepicker").datepicker();

	$("#subscribe").click(function() {
		var name = $('#name').val(),
			number = $('#number').val(),
			sms = $('sms').checked ? 'True' : 'False',
			call = $('call').checked ? 'True': 'False';
		if (name === undefined || number === undefined) {
			alert("You must enter a name and phone number!");
			return;
		}

		$.ajax({
			type: "POST",
			url: "/add-person",
			data: JSON.stringify({
				'name': name,
				'number': number,
				'sms': sms,
				'call': call
			}),
			contentType: 'application/json;charset=UTF-8',
		});

	});

	// Sending SMS messages or making calls 
	$("#text").click(function() {
		var msg = $("#message").val()
		if (!checkMessageLength(msg)) {
			return;
		}
		$.ajax({
			type: "POST",
			url: "/text",
			data: JSON.stringify({
				'message': $("#message").val()
			}),
			contentType: 'application/json;charset=UTF-8',
			success: function(result) {
				alert(result);
			}
		});
	});
	$("#call").click(function() {
		$.ajax({
			type: "POST",
			url: "/call",
			data: JSON.stringify({
				'message': $("#message").val(),
			}),
			contentType: 'application/json;charset=UTF-8',
			success: function(result) {
				alert(result);
			}
		});
	});

	function checkMessageLength(msg) {
		if (msg === undefined || msg.length === 0) {
			var error = "Please enter a message."
			$("#msg-error").text(error);
			return false;
		}
		else if (msg.length > 140) {
			var warning = "Your message may exceed 160 characters, which means more than 1 SMS message may be sent to each person. Is this okay? Click Yes to send, and click No to edit your message."
			return confirm(warning);

		}
		return true;
	}
});
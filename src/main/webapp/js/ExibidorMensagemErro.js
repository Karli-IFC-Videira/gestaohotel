document.addEventListener('DOMContentLoaded', function() {
	const errorMessage = document.querySelector('.alert-danger');
	if (errorMessage && errorMessage.textContent.trim() !== '') {
		errorMessage.style.display = 'block';
	}
});
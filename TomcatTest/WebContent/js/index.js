$(function() {
	var personFlag = 0
	var userFlag = false
	const $personUsername = $('input[name="username"]').eq(0)
	const $personAge = $('input[name="age"]')
	const $personName = $('input[name="name"]')
	const $personTeleno = $('input[name="teleno"]')
	const $personSubmit = $('input[type="submit"]').eq(0)
	const correctImgs = $('.correct-img')
	const wrongImgs = $('.wrong-img')

	const $userUsername = $('input[name="username"]').eq(1)
	const $userSubmit = $('input[type="submit"]').eq(1)

	function showCorrect (dom) {
		dom.next().remove()
		const img = '<img class="correct-img"  src="./images/correct.png" />'
		dom.after(img)
	}
	function showWrong (dom) {
		dom.next().remove()
		const img = '<img class="wrong-img"  src="./images/wrong.png" />'
		dom.after(img)
	}

	function watchPersonFlag () {
		if (personFlag === 4) {
			$personSubmit.attr('disabled', false)
		} else {
			$personSubmit.attr('disabled', 'disabled')
		}
	}

	function watchUserFlag () {
		if (userFlag) {
			$userSubmit.attr('disabled', false)
		} else {
			$userSubmit.attr('disabled', 'disabled')
		}
	}

	function initEvents () {
		$personUsername.on('change', (e) => {
			let us = $personUsername.val()

			if (us.length >= 4 && us.length <= 12) {
				personFlag++
				showCorrect($personUsername)
			} else {
				personFlag--
				showWrong($personUsername)
			}
			watchPersonFlag()
		})
		$personAge.on('change', (e) => {
			let age = parseInt($personAge.val())

			if (age >= 0 && age <= 99) {
				personFlag++
				showCorrect($personAge)
			} else {
				personFlag--
				showWrong($personAge)
			}
			watchPersonFlag()
		})
		$personName.on('change', (e) => {
			let us = $personName.val()

			if (us.length >= 4 && us.length <= 12) {
				personFlag++
				showCorrect($personName)
			} else {
				personFlag--
				showWrong($personName)
			}
			watchPersonFlag()
		})
		$personTeleno.on('change', (e) => {
			let tele = $personTeleno.val()

			if (tele.length === 11) {
				personFlag++
				showCorrect($personTeleno)
			} else {
				personFlag--
				showWrong($personTeleno)
			}
			watchPersonFlag()
		})

		$userUsername.on('change', (e) => {
			let us = $userUsername.val()

			if (us.length >= 4 && us.length <= 12) {
				userFlag = true
				showCorrect($userUsername)
			} else {
				userFlag = false
				showWrong($userUsername)
			}
			watchUserFlag()
		})
	}

	initEvents()

	
})
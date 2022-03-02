function payment() {
	
	let p_name = $('#product_name').text();
	let p_price = $('#product_price').val();
	let p_quantity = $('#quantity').val();
	
	let m_name = $('#member_name').val();
	let m_email = $('#member_email').val();
	let m_phone = $('#member_phone').val();
	let m_addr = $('#member_addr').val();
	
	let m_addr_split = m_addr.split('!');
	
	let total_amount = p_price * p_quantity;
	
	alert(p_price);
	alert(p_quantity);
	alert(total_amount);
	
	var IMP = window.IMP; // 생략가능
	IMP.init('imp60273439');
	IMP.request_pay({
		pg: 'kakaopay.TC0ONETIME', // version 1.1.0부터 지원.
		pay_method: 'card',
	
		merchant_uid: 'test' + new Date().getTime(),
	
		name: p_name,
		amount: total_amount,
		buyer_email: m_email,
		buyer_name: m_name,
		buyer_tel: m_phone,
		buyer_addr: m_addr_split[0] + m_addr_split[1],
		buyer_postcode: m_addr_split[2],
		m_redirect_url : 'redirect url'
	}, function (rsp) {
		console.log(rsp);
		if (rsp.success) {
			var msg = '결제가 완료되었습니다.';
			msg += '고유ID : ' + rsp.imp_uid;
			msg += '상점 거래ID : ' + rsp.merchant_uid;
			msg += '결제 금액 : ' + rsp.paid_amount;
			msg += '카드 승인번호 : ' + rsp.apply_num;
		} else {
			var msg = '결제에 실패하였습니다.';
			msg += '에러내용 : ' + rsp.error_msg;
		}
		alert(msg);
	});
	
}

$(function() {
	$("#kakao_pay").click(function() {
		payment();
	})
})
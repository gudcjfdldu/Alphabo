<?php
    // 헤더 부분
    $headers = array(
            'Content-Type:application/json',
            'Authorization:key=AIzaSyC5fTFHUoe5LlTY8ug0yqe90e-a6yzcd5M'
            );



    // 푸시 내용, data 부분을 자유롭게 사용해 클라이언트에서 분기할 수 있음.
    $arr = array();
    $arr['notification'] = array();
    $arr['to']= '/topics/noti';
    $arr['notification']['title'] = '전체공지';
    $arr['notification']['body'] = '알파보하고 함께 가위바위보한게임 어떠세요?';
    #$arr['registration_ids'] = $regid;
    #$arr['priority'] = 'high';
    #$arr['time_to_live']=2419200;
    #$arr['content_available']=true;
 //$arr['registration_ids'][0] = $regid[4];

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send');
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_POSTFIELDS,json_encode($arr));
    $response = curl_exec($ch);
    curl_close($ch);

    // 푸시 전송 결과 반환.
    $obj = json_decode($response);

    // 푸시 전송시 성공 수량 반환.
    $cnt = $obj->{"success"};
    echo (json_encode($arr));
    echo $cnt;
?>
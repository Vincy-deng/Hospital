import request from '@/utils/request'
export function getRecordByUid(data){
    return request({
        url: '/Record/getRecordByUid',
        method: 'get',
        params: data
    })
}


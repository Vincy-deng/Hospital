import request from '@/utils/request'
export function addAppointment(data){
    return request({
        url: '/patientController/addAppointment',
        method: 'post',
        data
    })
}

export function addPatien(data){
    return request({
        url: '/patientController/addPatien',
        method: 'post',
        data
    })
}

export function delAppointment(data){
    return request({
        url: '/patientController/delAppointment',
        method: 'post',
        data
    })
}

export function AppointmentList(data){
    return request({
        url: '/patientController/list',
        method: 'get',
        params: data
    })
}

export function updateAppointment(data){
    return request({
        url: '/patientController/updateAppointment',
        method: 'post',
        data
    })
}

export function getAppointmentByDid(data){
    return request({
        url: '/patientController/getAppointmentByDid',
        method: 'get',
        params: data
    })
}
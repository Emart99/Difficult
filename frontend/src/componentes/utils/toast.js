import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

export function MostrarError(message){
    toast.error(message, {
        position: "top-right",
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        draggable: true
    });
}
export function MostrarAgregado(message){
    toast.success(message, {
        autoClose: 3000,
        hideProgressBar: false,
        closeOnClick: true,
        draggable: true,

    });
}

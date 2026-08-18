import * as Dialog from "@radix-ui/react-dialog";
import {X} from "lucide-react";
import type {ReactNode} from "react";
import styles from "./Modal.module.css";

interface ModalProps {
    open: boolean
    onOpenChange: (open: boolean) => void;
    title: string;
    children: ReactNode;
}

const Modal = ({open, onOpenChange, title, children}: ModalProps) => {
    return (
        <Dialog.Root open={open} onOpenChange={onOpenChange}>
            <Dialog.Portal>
                <div className={styles.modal}>
                    <Dialog.Overlay className={styles.overlay} />

                    <Dialog.Content className={styles.content}>
                        <div className={styles.header}>
                            <Dialog.Title className={styles.title}>{title}</Dialog.Title>
                            <Dialog.Close asChild className={styles.closeContainer}>
                                <button className={styles.close}><X size={20}/></button>
                            </Dialog.Close>
                        </div>
                        {children}
                    </Dialog.Content>
                </div>
            </Dialog.Portal>
        </Dialog.Root>
    )
}

export default Modal;
import {AlertDialog} from "radix-ui";
import styles from "./ConfirmDialog.module.css";
import Button from "../common/Button.tsx";

interface ConfirmDialogProps {
    open: boolean;
    onOpenChange: (open: boolean) => void;
    onAction: () => void;
    message: string;
    actionLabel: string;
}

const ConfirmDialog = ({open, onOpenChange, onAction, message, actionLabel}: ConfirmDialogProps) => {
    return (
        <AlertDialog.Root open={open} onOpenChange={onOpenChange}>
            <AlertDialog.Portal>
                <div className={styles.confirm}>
                    <AlertDialog.Overlay className={styles.overlay}/>

                    <AlertDialog.Content className={styles.content}>
                        <AlertDialog.Title className={styles.title}>Are you sure?</AlertDialog.Title>
                        <AlertDialog.Description className={styles.description}>
                            {message}
                        </AlertDialog.Description>
                        <div className={styles.buttons}>
                            <AlertDialog.Cancel asChild>
                                <Button variant="secondary" className={styles.cancel}>Cancel</Button>
                            </AlertDialog.Cancel>
                            <AlertDialog.Action asChild>
                                <Button variant="primary" onClick={onAction} className={styles.action}>{actionLabel}</Button>
                            </AlertDialog.Action>
                        </div>
                    </AlertDialog.Content>
                </div>
            </AlertDialog.Portal>
        </AlertDialog.Root>
    )
}

export default ConfirmDialog;
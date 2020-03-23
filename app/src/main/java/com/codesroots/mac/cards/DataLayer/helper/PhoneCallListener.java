package com.codesroots.mac.cards.DataLayer.helper;

import android.telephony.CellInfo;
import android.telephony.CellLocation;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.widget.TextView;

import java.util.List;
public class PhoneCallListener extends PhoneStateListener
{
    private boolean isPhoneCalling = false;

    @Override
    public void onCellInfoChanged(List<CellInfo> cellInfo) {
        super.onCellInfoChanged(cellInfo);

    }

    @Override
    public void onMessageWaitingIndicatorChanged(boolean mwi) {
        super.onMessageWaitingIndicatorChanged(mwi);


    }


    @Override
    public void onCallStateChanged(int state, String incomingNumber)
    {
        if (TelephonyManager.CALL_STATE_RINGING == state)
        {
            // phone ringing
        }

        if (TelephonyManager.CALL_STATE_OFFHOOK == state)
        {
            // active
            isPhoneCalling = true;
        }

        if (TelephonyManager.CALL_STATE_IDLE == state)
        {
            // run when class initial and phone call ended, need detect flag
            // from CALL_STATE_OFFHOOK

        }
    }
}

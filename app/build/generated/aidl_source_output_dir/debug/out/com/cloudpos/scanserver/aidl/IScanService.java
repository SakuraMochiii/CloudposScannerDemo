/*
 * This file is auto-generated.  DO NOT MODIFY.
 */
package com.cloudpos.scanserver.aidl;
public interface IScanService extends android.os.IInterface
{
  /** Default implementation for IScanService. */
  public static class Default implements com.cloudpos.scanserver.aidl.IScanService
  {
    @Override public com.cloudpos.scanserver.aidl.ScanResult scanBarcode(com.cloudpos.scanserver.aidl.ScanParameter parameter) throws android.os.RemoteException
    {
      return null;
    }
    @Override public void startScan(com.cloudpos.scanserver.aidl.ScanParameter parameter, com.cloudpos.scanserver.aidl.IScanCallBack callBack) throws android.os.RemoteException
    {
    }
    @Override public boolean stopScan() throws android.os.RemoteException
    {
      return false;
    }
    // Return type for index is Scanner(hardware) or Camera.

    @Override public java.lang.String getScanType(int index) throws android.os.RemoteException
    {
      return null;
    }
    @Override
    public android.os.IBinder asBinder() {
      return null;
    }
  }
  /** Local-side IPC implementation stub class. */
  public static abstract class Stub extends android.os.Binder implements com.cloudpos.scanserver.aidl.IScanService
  {
    private static final java.lang.String DESCRIPTOR = "com.cloudpos.scanserver.aidl.IScanService";
    /** Construct the stub at attach it to the interface. */
    public Stub()
    {
      this.attachInterface(this, DESCRIPTOR);
    }
    /**
     * Cast an IBinder object into an com.cloudpos.scanserver.aidl.IScanService interface,
     * generating a proxy if needed.
     */
    public static com.cloudpos.scanserver.aidl.IScanService asInterface(android.os.IBinder obj)
    {
      if ((obj==null)) {
        return null;
      }
      android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
      if (((iin!=null)&&(iin instanceof com.cloudpos.scanserver.aidl.IScanService))) {
        return ((com.cloudpos.scanserver.aidl.IScanService)iin);
      }
      return new com.cloudpos.scanserver.aidl.IScanService.Stub.Proxy(obj);
    }
    @Override public android.os.IBinder asBinder()
    {
      return this;
    }
    @Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
    {
      java.lang.String descriptor = DESCRIPTOR;
      switch (code)
      {
        case INTERFACE_TRANSACTION:
        {
          reply.writeString(descriptor);
          return true;
        }
        case TRANSACTION_scanBarcode:
        {
          data.enforceInterface(descriptor);
          com.cloudpos.scanserver.aidl.ScanParameter _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.cloudpos.scanserver.aidl.ScanParameter.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          com.cloudpos.scanserver.aidl.ScanResult _result = this.scanBarcode(_arg0);
          reply.writeNoException();
          if ((_result!=null)) {
            reply.writeInt(1);
            _result.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
          }
          else {
            reply.writeInt(0);
          }
          return true;
        }
        case TRANSACTION_startScan:
        {
          data.enforceInterface(descriptor);
          com.cloudpos.scanserver.aidl.ScanParameter _arg0;
          if ((0!=data.readInt())) {
            _arg0 = com.cloudpos.scanserver.aidl.ScanParameter.CREATOR.createFromParcel(data);
          }
          else {
            _arg0 = null;
          }
          com.cloudpos.scanserver.aidl.IScanCallBack _arg1;
          _arg1 = com.cloudpos.scanserver.aidl.IScanCallBack.Stub.asInterface(data.readStrongBinder());
          this.startScan(_arg0, _arg1);
          reply.writeNoException();
          return true;
        }
        case TRANSACTION_stopScan:
        {
          data.enforceInterface(descriptor);
          boolean _result = this.stopScan();
          reply.writeNoException();
          reply.writeInt(((_result)?(1):(0)));
          return true;
        }
        case TRANSACTION_getScanType:
        {
          data.enforceInterface(descriptor);
          int _arg0;
          _arg0 = data.readInt();
          java.lang.String _result = this.getScanType(_arg0);
          reply.writeNoException();
          reply.writeString(_result);
          return true;
        }
        default:
        {
          return super.onTransact(code, data, reply, flags);
        }
      }
    }
    private static class Proxy implements com.cloudpos.scanserver.aidl.IScanService
    {
      private android.os.IBinder mRemote;
      Proxy(android.os.IBinder remote)
      {
        mRemote = remote;
      }
      @Override public android.os.IBinder asBinder()
      {
        return mRemote;
      }
      public java.lang.String getInterfaceDescriptor()
      {
        return DESCRIPTOR;
      }
      @Override public com.cloudpos.scanserver.aidl.ScanResult scanBarcode(com.cloudpos.scanserver.aidl.ScanParameter parameter) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        com.cloudpos.scanserver.aidl.ScanResult _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((parameter!=null)) {
            _data.writeInt(1);
            parameter.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          boolean _status = mRemote.transact(Stub.TRANSACTION_scanBarcode, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().scanBarcode(parameter);
          }
          _reply.readException();
          if ((0!=_reply.readInt())) {
            _result = com.cloudpos.scanserver.aidl.ScanResult.CREATOR.createFromParcel(_reply);
          }
          else {
            _result = null;
          }
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      @Override public void startScan(com.cloudpos.scanserver.aidl.ScanParameter parameter, com.cloudpos.scanserver.aidl.IScanCallBack callBack) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          if ((parameter!=null)) {
            _data.writeInt(1);
            parameter.writeToParcel(_data, 0);
          }
          else {
            _data.writeInt(0);
          }
          _data.writeStrongBinder((((callBack!=null))?(callBack.asBinder()):(null)));
          boolean _status = mRemote.transact(Stub.TRANSACTION_startScan, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            getDefaultImpl().startScan(parameter, callBack);
            return;
          }
          _reply.readException();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
      }
      @Override public boolean stopScan() throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        boolean _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          boolean _status = mRemote.transact(Stub.TRANSACTION_stopScan, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().stopScan();
          }
          _reply.readException();
          _result = (0!=_reply.readInt());
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      // Return type for index is Scanner(hardware) or Camera.

      @Override public java.lang.String getScanType(int index) throws android.os.RemoteException
      {
        android.os.Parcel _data = android.os.Parcel.obtain();
        android.os.Parcel _reply = android.os.Parcel.obtain();
        java.lang.String _result;
        try {
          _data.writeInterfaceToken(DESCRIPTOR);
          _data.writeInt(index);
          boolean _status = mRemote.transact(Stub.TRANSACTION_getScanType, _data, _reply, 0);
          if (!_status && getDefaultImpl() != null) {
            return getDefaultImpl().getScanType(index);
          }
          _reply.readException();
          _result = _reply.readString();
        }
        finally {
          _reply.recycle();
          _data.recycle();
        }
        return _result;
      }
      public static com.cloudpos.scanserver.aidl.IScanService sDefaultImpl;
    }
    static final int TRANSACTION_scanBarcode = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
    static final int TRANSACTION_startScan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
    static final int TRANSACTION_stopScan = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
    static final int TRANSACTION_getScanType = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
    public static boolean setDefaultImpl(com.cloudpos.scanserver.aidl.IScanService impl) {
      // Only one user of this interface can use this function
      // at a time. This is a heuristic to detect if two different
      // users in the same process use this function.
      if (Stub.Proxy.sDefaultImpl != null) {
        throw new IllegalStateException("setDefaultImpl() called twice");
      }
      if (impl != null) {
        Stub.Proxy.sDefaultImpl = impl;
        return true;
      }
      return false;
    }
    public static com.cloudpos.scanserver.aidl.IScanService getDefaultImpl() {
      return Stub.Proxy.sDefaultImpl;
    }
  }
  public com.cloudpos.scanserver.aidl.ScanResult scanBarcode(com.cloudpos.scanserver.aidl.ScanParameter parameter) throws android.os.RemoteException;
  public void startScan(com.cloudpos.scanserver.aidl.ScanParameter parameter, com.cloudpos.scanserver.aidl.IScanCallBack callBack) throws android.os.RemoteException;
  public boolean stopScan() throws android.os.RemoteException;
  // Return type for index is Scanner(hardware) or Camera.

  public java.lang.String getScanType(int index) throws android.os.RemoteException;
}

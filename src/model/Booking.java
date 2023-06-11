package model;
import enuns.FormaPagamento;

import java.sql.Date;


public class Booking {
    private Long id;
    private Date checkIn;
    private Date checkOut;
    private double valor;
    private FormaPagamento formaPagamento;

    public Booking( ) {
    }

    public Booking(Date checkIn, Date checkOut, String valor, String formaPagamento) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valor = Double.parseDouble(valor);
        this.formaPagamento = FormaPagamento.valueOf(formaPagamento);
    }


    public Booking(long id, Date checkIn, Date checkOut, double valor, String formaPagamento) {
        this.id = id;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.valor = valor;
        this.formaPagamento = FormaPagamento.valueOf(formaPagamento);
    }

    public Long getId() {
        return id;
    }

    public Date getcheckInDate() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public double getValor() {
        return valor;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setcheckInDate(Date checkInDate
    ) {
        this.checkIn = checkInDate;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}

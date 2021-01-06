import React, {Component} from "react";
import cookie from "react-cookies";
import {Button, ButtonGroup, ToggleButton} from "react-bootstrap";

class Lajkovi extends Component{
    constructor(props) {
        super(props);
        this.state = {
            user: cookie.load('principal'),
            oglas: '',
            isLoggedIn: cookie.load('isAuth'),
            ocjena: ''
        }
    }

    ocjene = [
        {name: 'Sviđa mi se🙂', value: '1'},
        {name: 'Jako mi se sviđa😁', value: '2'},
        {name: 'To je to😍', value: '3'},
        {name: 'Nemoj više prikazivati❌', value: '4'},
    ];

    async componentDidMount() {
        let self = this;

        let user = self.state.user;
        let oglas = self.props.oglas;

        if(this.state.isLoggedIn) {
            //ovo za fetchanje oglasa je ovdje jer inace baca nullpointer
            const optionsOglas = {
                method: 'GET',
                headers: {
                    'Access-Control-Allow-Origin': '*'
                }
            };

            fetch(`${process.env.REACT_APP_BACKEND_URL}/oglas/getoglas?oglas_id=${self.props.oglasId}`, optionsOglas)
                .then(response => {
                    if (response.status === 200) {
                        response.json().then(body => {
                            self.setState({oglas: body})
                        }).catch(error => console.log(error))
                    }
                });


            const optionsLajk = {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Access-Control-Allow-Origin': '*'
                }
            };

            await fetch(`${process.env.REACT_APP_BACKEND_URL}/lajk/ocjena?student_username=${this.state.user.korisnickoIme}&oglas_id=${self.props.oglasId}`, optionsLajk)
                .then(response => {
                    response.text().then(body => {
                        self.setState({ocjena: body})
                    });
                }).catch(error => console.log(error));
        }
    }

    change = (e) => {
        let self = this;
        let ocjena = e.target.value;
        let user = self.state.user;
        let oglas = self.state.oglas;
        this.setState({ocjena: ocjena});

        const options = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        };

        fetch(`${process.env.REACT_APP_BACKEND_URL}/lajk/update?student_username=${user.korisnickoIme}&oglas_id=${oglas.id}&ocjena=${ocjena}`, options)
            .then(response => {
                if (response.status === 200) {
                } else {
                    response.text().then(body => {
                        console.log(body);
                    });
                }
            }).catch(error => console.log(error));
    };

    clearInput = () => {
        let self = this;
        let user = self.state.user;
        let oglas = self.state.oglas;
        this.setState({ocjena: ''});

        const options = {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
            }
        };

        fetch(`${process.env.REACT_APP_BACKEND_URL}/lajk/delete?student_username=${user.korisnickoIme}&oglas_id=${oglas.id}`, options)
            .then(response => {
                if (response.status === 200) {
                } else {
                    response.text().then(body => {
                        console.log(body);
                    });
                }
            }).catch(error => console.log(error));
    }
    render() {
        return (
            <ButtonGroup size="sm" toggle>
                {this.ocjene.map((like, idx) => (
                    <ToggleButton
                        key={idx}
                        type="radio"
                        variant="outline-secondary"
                        value={like.value}
                        disabled={!this.state.isLoggedIn}
                        checked={this.state.ocjena === like.value}
                        onChange={(e) => this.change(e)}
                    >
                        {like.name}
                    </ToggleButton>
                ))}
                <Button variant="outline-secondary" onClick={this.clearInput}>Poništi odabir</Button>
            </ButtonGroup>
        )
    }
}
export default Lajkovi;
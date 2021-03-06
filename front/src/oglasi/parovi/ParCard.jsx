import React, {Component} from 'react';
import SobaReadOnly from "../SobaReadOnly";
import {Button, Col, Row} from "react-bootstrap";

export default class ParCard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            par: props.par
        };

        this.par = props.par;
        this.oglas = props.par.oglas1.student === props.user.korisnickoIme ? props.par.oglas2 : props.par.oglas1
    }

    onUpdatePar = (potvrdeno) => {
        let self = this
        const options = {
            method: 'POST',
            headers: {
                'Access-Control-Allow-Origin': '*'
            }
        };

        fetch(`${process.env.REACT_APP_BACKEND_URL}/oglas/updatePar?par_id=${this.par.parID}&par2_id=${null}&odobren=${potvrdeno}&student_username=${this.props.user.korisnickoIme}`, options)
            .then(response => {
                if (response.status === 200) {
                    return response.json()
                }
            }).then(json => {
            self.setState(json)
        }).catch(() => console.log("korisnik nema oglase?"))
    };

    onPrihvati = () => {
        this.onUpdatePar(true)
    };

    onOdbij = () => {
        this.onUpdatePar(false)
    };

    render() {
        return (
            <div className={"innerForm"}>
                <Row>
                    <SobaReadOnly horizontal={true} title={this.oglas.student} soba={this.oglas.soba}/>
                </Row>
                <Row>
                    <Col>
                        <Button disabled={this.props.postojiPotvrden} className={"yes"} block onClick={this.onPrihvati}>Prihvati</Button>
                    </Col>
                    <Col>
                        <Button disabled={this.props.postojiPotvrden} className={"no"} block onClick={this.onOdbij}>Odbij</Button>
                    </Col>
                </Row>
                <br/>
                <Row>
                    Kada jednom prihvatite ili odbijete zamjenu ne možete više promijeniti svoj izbor!
                </Row>
            </div>
        )
    }

}

import React, {Component} from "react";
import {Link} from "react-router-dom";
import Lajkovi from "../../components/Lajkovi";
import * as cookie from "react-cookies";
import SobaReadOnly from "../SobaReadOnly";
import {Form} from "react-bootstrap";

class OglasCard extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: cookie.load('principal')
        }
    }

    render() {
        let oglas = this.props.oglas;
        return (
            <a href={`/oglas/id=${oglas.id}`}>
                <div className={"Card"}>
                    <SobaReadOnly soba={oglas.soba} title={oglas.student}/>

                    <br/>
                    <Lajkovi oglasId={oglas.id} user={this.state.user}/>
                </div>
            </a>
        )
    }

}

export default OglasCard;

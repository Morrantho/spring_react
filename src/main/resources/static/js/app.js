import React, {Component} from 'react';
import ReactDOM from 'react-dom';

class App extends Component{
	constructor(props) {
		super(props);

		this.user = {
			"name":"",
			"email":"",
			"password":""
		};

		this.state={
			users:[],
		};
	}

	init(){
		fetch("/users").then(data=>data.json())
		.then(data=>{
			let users = data.map((user)=>{
				return (
				<tr key={user.id}>
					<td>{user.name}</td>
					<td>{user.email}</td>
					<td>{user.password}</td>
				</tr>
			)})

			this.setState({users:users});
		})
	}

	onSubmit(e){
		e.preventDefault();

		let formData = new FormData();
		formData.append("name",this.user.name);
		formData.append("email",this.user.email);
		formData.append("password",this.user.password);

		fetch("/users/new",{
			method:"post",
			body:formData
		}).then(data=>data.json())
		.then(data=>{
			let u = (
				<tr key={data.id}>
					<td>{data.name}</td>
					<td>{data.email}</td>
					<td>{data.password}</td>
				</tr>
			);
		
			this.state.users.push(u);
			this.setState({users:this.state.users});
		})
	}

	onChange(e){
		let key  = e.target.name;
		let val  = e.target.value;
		// Loop user members. If match, set corresponding value.
		for(let k in this.user){
			if(k == key) this.user[key] = val;
		}
	}

	render(){
		return (
			<div>
				<h1>User</h1>
				<h1>{this.state.user}</h1>

				<table>
					<thead>
						<tr>
							<th>Name:</th>
							<th>Email:</th>
							<th>Password:</th>
						</tr>
					</thead>

					<tbody>
						{this.state.users}
					</tbody>
				</table>

				<form onSubmit={this.onSubmit.bind(this)}>
					<input name="name" onChange={this.onChange.bind(this)} type="text" />
					<input name="email" onChange={this.onChange.bind(this)} type="text" />
					<input name="password" onChange={this.onChange.bind(this)} type="text" />
					<input type="submit" value="Create User" />
				</form>
			</div>
		)
	}
	componentDidMount(){this.init();}
}

ReactDOM.render(
	<App />,
	document.getElementById('react')
)


/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.portal.kernel.cluster;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.net.InetAddress;

/**
 * @author Tina Tian
 */
public class ClusterNode implements Comparable<ClusterNode>, Serializable {

	public ClusterNode(String clusterNodeId) {
		_clusterNodeId = clusterNodeId;
	}

	public int compareTo(ClusterNode clusterNode) {
		InetAddress inetAddress = clusterNode._inetAddress;

		String ipAddress = inetAddress.getHostAddress();

		String curIpAddress = _inetAddress.getHostAddress();

		int value = curIpAddress.compareTo(ipAddress);

		if (value == 0) {
			if (_port > clusterNode._port) {
				value = 1;
			}
			else if (_port < clusterNode._port) {
				value = -1;
			}
		}

		return value;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof ClusterNode)) {
			return false;
		}

		ClusterNode clusterNode = (ClusterNode)obj;

		if (Validator.equals(_clusterNodeId, clusterNode._clusterNodeId)) {
			return true;
		}

		return false;
	}

	public String getClusterNodeId() {
		return _clusterNodeId;
	}

	public String getHostName() {
		return _hostName;
	}

	public InetAddress getInetAddress() {
		return _inetAddress;
	}

	public int getPort() {
		return _port;
	}

	@Override
	public int hashCode() {
		return _clusterNodeId.hashCode();
	}

	public void setHostName(String hostName) {
		_hostName = hostName;
	}

	public void setInetAddress(InetAddress inetAddress) {
		_inetAddress = inetAddress;
	}

	public void setPort(int port) {
		_port = port;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{clusterNodeId=");
		sb.append(_clusterNodeId);
		sb.append(", hostName=");
		sb.append(_hostName);
		sb.append(", inetAddress=");
		sb.append(_inetAddress);
		sb.append(", port=");
		sb.append(_port);
		sb.append("}");

		return sb.toString();
	}

	private String _clusterNodeId;
	private String _hostName;
	private InetAddress _inetAddress;
	private int _port;

}
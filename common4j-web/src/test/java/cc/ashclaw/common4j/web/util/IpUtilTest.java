// SPDX-License-Identifier: Apache-2.0
// Copyright (c) 2026 AshClaw.

package cc.ashclaw.common4j.web.util;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IpUtilTest {

    @Mock
    private HttpServletRequest request;

    @Test
    void testGetClientIpWithNullRequest() {
        assertThrows(IllegalArgumentException.class, () -> IpUtil.getClientIp(null));
    }

    @Test
    void testGetClientIpFromXForwardedFor() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("192.168.1.1, 10.0.0.1");
        assertEquals("192.168.1.1", IpUtil.getClientIp(request));
    }

    @Test
    void testGetClientIpFromProxyClientIp() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn("172.16.0.1");
        assertEquals("172.16.0.1", IpUtil.getClientIp(request));
    }

    @Test
    void testGetClientIpFromWlProxyClientIp() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn("192.168.0.100");
        assertEquals("192.168.0.100", IpUtil.getClientIp(request));
    }

    @Test
    void testGetClientIpFromHttpClientIp() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn("10.10.10.10");
        assertEquals("10.10.10.10", IpUtil.getClientIp(request));
    }

    @Test
    void testGetClientIpFromHttpXForwardedFor() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(null);
        when(request.getHeader("HTTP_X_FORWARDED_FOR")).thenReturn("8.8.8.8");
        assertEquals("8.8.8.8", IpUtil.getClientIp(request));
    }

    @Test
    void testGetClientIpFromRemoteAddr() {
        when(request.getHeader("X-Forwarded-For")).thenReturn(null);
        when(request.getHeader("Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("WL-Proxy-Client-IP")).thenReturn(null);
        when(request.getHeader("HTTP_CLIENT_IP")).thenReturn(null);
        when(request.getHeader("HTTP_X_FORWARDED_FOR")).thenReturn(null);
        when(request.getRemoteAddr()).thenReturn("127.0.0.1");
        assertEquals("127.0.0.1", IpUtil.getClientIp(request));
    }

    @Test
    void testGetClientIpWithUnknownHeader() {
        when(request.getHeader("X-Forwarded-For")).thenReturn("unknown");
        assertEquals("unknown", IpUtil.getClientIp(request));
    }

    @Test
    void testIsValidIpAddress() {
        assertTrue(IpUtil.isValidIpAddress("192.168.1.1"));
        assertTrue(IpUtil.isValidIpAddress("10.0.0.1"));
        assertTrue(IpUtil.isValidIpAddress("255.255.255.255"));
        assertTrue(IpUtil.isValidIpAddress("0.0.0.0"));
    }

    @Test
    void testIsValidIpAddressInvalid() {
        assertFalse(IpUtil.isValidIpAddress(null));
        assertFalse(IpUtil.isValidIpAddress(""));
        assertFalse(IpUtil.isValidIpAddress("not.an.ip.address"));
        assertFalse(IpUtil.isValidIpAddress("256.1.1.1"));
        assertFalse(IpUtil.isValidIpAddress("192.168.1"));
    }

    @Test
    void testIsIpInCidrValidIpv4() {
        assertTrue(IpUtil.isIpInCidr("192.168.1.100", "192.168.1.0/24"));
        assertFalse(IpUtil.isIpInCidr("192.168.2.100", "192.168.1.0/24"));
    }

    @Test
    void testIsIpInCidrIpv4Slash32() {
        assertTrue(IpUtil.isIpInCidr("192.168.1.1", "192.168.1.1/32"));
        assertFalse(IpUtil.isIpInCidr("192.168.1.2", "192.168.1.1/32"));
    }

    @Test
    void testIsIpInCidrIpv4Slash0() {
        assertTrue(IpUtil.isIpInCidr("192.168.1.1", "0.0.0.0/0"));
        assertTrue(IpUtil.isIpInCidr("255.255.255.255", "0.0.0.0/0"));
    }

    @Test
    void testIsIpInCidrInvalidParams() {
        assertFalse(IpUtil.isIpInCidr(null, "192.168.1.0/24"));
        assertFalse(IpUtil.isIpInCidr("192.168.1.1", null));
        assertFalse(IpUtil.isIpInCidr("", "192.168.1.0/24"));
        assertFalse(IpUtil.isIpInCidr("192.168.1.1", ""));
    }

    @Test
    void testIsIpInCidrInvalidCidrFormat() {
        assertThrows(IllegalArgumentException.class, () -> IpUtil.isIpInCidr("192.168.1.1", "192.168.1.0"));
        assertThrows(IllegalArgumentException.class, () -> IpUtil.isIpInCidr("192.168.1.1", "192.168.1.0/33"));
        assertThrows(IllegalArgumentException.class, () -> IpUtil.isIpInCidr("192.168.1.1", "192.168.1.0/-1"));
    }

    @Test
    void testIsIpInCidrMixedIpVersions() {
        assertThrows(IllegalArgumentException.class, () -> IpUtil.isIpInCidr("192.168.1.1", "::1/128"));
    }

    @Test
    void testIsIpInCidrIpv6() {
        assertTrue(IpUtil.isIpInCidr("2001:0db8:0000:0000:0000:0000:0000:0001", "2001:0db8:0000:0000:0000:0000:0000:0000/32"));
    }

    @Test
    void testIsIpInCidrIpv6InvalidPrefixLength() {
        assertThrows(IllegalArgumentException.class, () -> IpUtil.isIpInCidr("2001:db8::1", "2001:db8::/129"));
    }
}
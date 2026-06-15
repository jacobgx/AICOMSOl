/*
 * nonlinear_transfer_impedance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class nonlinear_transfer_impedance {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Nonlinear_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");
    model.component("comp1").physics().create("tatd", "ThermoacousticsSinglePhysicsTransient", "geom1");
    model.component("comp1").physics().create("actd", "TransientPressureAcoustics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);
    model.study("std1").feature("freq").setSolveFor("/physics/tatd", true);
    model.study("std1").feature("freq").setSolveFor("/physics/actd", true);

    model.param().label("\u53c2\u6570 1 - \u51e0\u4f55");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("ab", "0.75", "\u6bd4\u7387 a/b");
    model.param().set("ac", "0.4[mm]", "\u5b54\u4e2d\u5fc3\u534a\u5f84");
    model.param().set("tp", "1[mm]", "\u677f\u539a");
    model.param().set("delta", "ac*(1-ab)/(1+ab)", "\u534a\u5f84\u504f\u79fb");
    model.param().set("a", "ac-delta", "\u5b54\u5e95\u534a\u5f84");
    model.param().set("b", "ac+delta", "\u5b54\u9876\u534a\u5f84");
    model.param().set("Lpml", "0.5[mm]", "PML \u539a\u5ea6");
    model.param().set("N0", "50", "\u5706\u89d2\u534a\u5f84\u5206\u6570");
    model.param().set("r0", "ac/N0", "\u5706\u89d2\u534a\u5f84");
    model.param().set("NH", "25", "\u9ad8\u5ea6\u51e0\u4f55\u56e0\u5b50");
    model.param().set("NR", "6", "\u5f84\u5411\u51e0\u4f55\u56e0\u5b50");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570 2 - \u6a21\u578b");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2").set("fmax", "2000[Hz]", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param("par2")
         .set("dvisc", "220[um]*sqrt(100[Hz]/fmax)", "fmax \u7684\u9ecf\u6ede\u8fb9\u754c\u5c42\u539a\u5ea6");
    model.param("par2").set("f0", "500[Hz]", "\u9891\u7387");
    model.param("par2").set("omega0", "2*pi*f0", "\u89d2\u9891\u7387");
    model.param("par2").set("T0", "1/f0", "\u5468\u671f");
    model.param("par2").set("pin", "10[Pa]", "\u5165\u5c04\u538b\u529b\u5e45\u503c");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-tp/2", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "a", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-tp/2", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "b", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "tp/2", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "tp/2", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-tp/2", 4, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"ac", "0"});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"NR*ac", "NH*ac"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "tp/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"NR*ac", "NH*ac"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "tp/2-NH*ac-tp"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("ls1", "pol1", "r1", "r2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 4, 8);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("del1", 6, 8);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "r0");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"NR*ac", "NH*ac"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "tp/2+NH*ac"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"NR*ac", "NH*ac"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new String[]{"0", "tp/2-NH*ac-tp-NH*ac"});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").selection("vertex1").set("fil1", 2);
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new String[]{"a", "-tp/2"});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls3").selection("vertex1").set("fil1", 4);
    model.component("comp1").geom("geom1").feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls3").set("coord2", new String[]{"b", "tp/2"});
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf 1 - \u7ebf\u6027\u89e3\u6790\u6a21\u578b");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("Ztrans", "(intop_in(ta.p_t)/intop_in(1)-intop_out(ta.p_t)/intop_in(1))/(intop_mid(w)/intop_mid(1))/(rho0*c0)", "\u8f6c\u79fb\u963b\u6297");
    model.component("comp1").variable("var1").set("iomega0", "i*2*pi*freq", "\u590d\u89d2\u9891\u7387");
    model.component("comp1").variable("var1").set("kv", "sqrt(-rho0*iomega0/mu0)", "\u9ecf\u6027\u6ce2\u6570");
    model.component("comp1").variable("var1").set("kth", "sqrt(-iomega0*rho0*Cp0/kappa0)", "\u70ed\u6ce2\u6570");
    model.component("comp1").variable("var1").set("Gv", "-besselj(2,kv*a)/besselj(0,kv*a)", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1").set("Gth", "-besselj(2,kth*a)/besselj(0,kth*a)", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var1")
         .set("Zc", "1/sqrt((gamma0-(gamma0-1)*Gth)*Gv)", "\u7279\u6027\u963b\u6297");
    model.component("comp1").variable("var1")
         .set("kc", "2*pi*freq/c0*sqrt((gamma0-(gamma0-1)*Gth)/Gv)", "\u590d\u6ce2\u6570");
    model.component("comp1").variable("var1")
         .set("Zlin_NOec", "2*i*Zc*sin(kc*tp/2)", "\u7ebf\u6027\u8f6c\u79fb\u963b\u6297\uff08\u65e0\u7aef\u90e8\u4fee\u6b63\uff09");
    model.component("comp1").variable("var1").set("EC", "16*a/(3*pi)", "\u7aef\u90e8\u4fee\u6b63");
    model.component("comp1").variable("var1")
         .set("ECfit", "0.8", "\u7aef\u90e8\u4fee\u6b63\u62df\u5408\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("Zlin", "2*i*Zc*sin(kc*(tp+ECfit*EC)/2)", "\u7ebf\u6027\u8f6c\u79fb\u963b\u6297\uff08\u89e3\u6790\u89e3\uff09");
    model.component("comp1").variable("var1")
         .set("Ztrans_rms", "(abs(intop_in(ta.p_t))/intop_in(1)-abs(intop_out(ta.p_t))/intop_in(1))/(abs(intop_mid(w))/intop_mid(1))/(rho0*c0)", "\u6a21\u578b\u8f6c\u79fb\u963b\u6297\uff08\u5747\u65b9\u6839\uff09");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf 2 - \u6750\u6599\u53c2\u6570");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2").set("rho0", "intop_pnt(ta.rho0)", "\u5e73\u8861\u5bc6\u5ea6");
    model.component("comp1").variable("var2").set("mu0", "intop_pnt(ta.mu)", "\u9ecf\u5ea6");
    model.component("comp1").variable("var2").set("c0", "intop_pnt(ta.c)", "\u58f0\u901f");
    model.component("comp1").variable("var2").set("gamma0", "intop_pnt(ta.gamma)", "\u6bd4\u70ed\u7387");
    model.component("comp1").variable("var2").set("Cp0", "intop_pnt(ta.Cp)", "\u6052\u538b\u70ed\u5bb9");
    model.component("comp1").variable("var2").set("kappa0", "intop_pnt(ta.kcond)", "\u5bfc\u70ed\u7cfb\u6570");
    model.component("comp1").variable().create("var3");
    model.component("comp1").variable("var3").label("\u53d8\u91cf 3 - \u975e\u7ebf\u6027\u89e3\u6790\u6a21\u578b");

//    To import content from file, use:
//    model.component("comp1").variable("var3").loadFile("FILENAME");
    model.component("comp1").variable("var3").set("Cd", "0.7", "\u6d41\u91cf\u7cfb\u6570");
    model.component("comp1").variable("var3")
         .set("Znonlin", "rho0*sqrt(0.5*U*conj(U))*1/(2*Cd^2)/(rho0*c0)", "\u5bf9\u963b\u6297\u7684\u975e\u7ebf\u6027\u8d21\u732e");
    model.component("comp1").variable("var3")
         .set("U", "intop_mid(w)/intop_mid(1)", "\u5b54\u4e2d\u7684\u5e73\u5747\u901f\u5ea6");
    model.component("comp1").variable("var3")
         .set("U2", "sqrt(2)*w_rms", "\u5b54\u4e2d\u7684\u5e73\u5747\u901f\u5ea6");
    model.component("comp1").variable("var3")
         .set("Sr", "2*pi*freq*2*ac/abs(U)", "\u65af\u7279\u52b3\u54c8\u5c14\u6570\uff08\u9891\u7387\uff09");
    model.component("comp1").variable("var3")
         .set("Sr2", "omega0*2*ac/abs(U2)", "\u65af\u7279\u52b3\u54c8\u5c14\u6570\uff08\u77ac\u6001\uff09");
    model.component("comp1").variable("var3")
         .set("Sh", "2*ac*sqrt(2*pi*freq*rho0/(4*mu0))", "\u526a\u5207\u6570\uff08\u9891\u7387\uff09");
    model.component("comp1").variable("var3")
         .set("Sh2", "2*ac*sqrt(omega0*rho0/(4*mu0))", "\u526a\u5207\u6570\uff08\u77ac\u6001\uff09");
    model.component("comp1").variable("var3").set("FC", "1/(1+2*Sr*(1+0.06*exp(3.74/Sh)))", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var3").set("GC", "if(Sr>1,GC2,GC1)", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var3")
         .set("GC1", "0.2-0.5/Sr*(1-0.42/Sh^2)+0.05/Sr^2*(1-0.68/Sh^2)", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var3")
         .set("GC2", "-0.05*(1/Sr*(1-1/Sh))-0.6*(1/Sr*(1-1/Sh))^2", "\u52a9\u53d8\u91cf");
    model.component("comp1").variable("var3")
         .set("Rnonlin", "real(Zlin)+FC*U*rho0/(2*Cd^2)/(rho0*c0)", "\u975e\u7ebf\u6027\u7535\u963b\uff08Temiz \u7b49\u4eba\uff09");
    model.component("comp1").variable("var3")
         .set("Dnonlin", "imag(Zlin)+GC*2*pi*freq*rho0*2*a/(2*rho0*c0)", "\u975e\u7ebf\u6027\u7535\u6297\uff08Temiz \u7b49\u4eba\uff09");
    model.component("comp1").variable().create("var4");
    model.component("comp1").variable("var4").label("\u53d8\u91cf 4 - \u65f6\u57df\u963b\u6297");

//    To import content from file, use:
//    model.component("comp1").variable("var4").loadFile("FILENAME");
    model.component("comp1").variable("var4")
         .set("Ztime", "Dp_rms/w_rms/(rho0*c0)", "\u8f6c\u79fb\u963b\u6297\uff08\u57fa\u4e8e\u65f6\u57df\u5747\u65b9\u6839\uff09");
    model.component("comp1").variable("var4")
         .set("Dp_rms", "sqrt(1/(T0)*timeint(4*T0,5*T0,(intop_in(tatd.p_t)-intop_out(tatd.p_t))^2,1e-3,T0[1/s]/50))/intop_in(1)", "\u538b\u964d\u5747\u65b9\u6839");
    model.component("comp1").variable("var4")
         .set("pin_rms", "sqrt(1/(T0)*timeint(4*T0,5*T0,intop_in(tatd.p_t)^2,1e-3,T0[1/s]/50))/intop_in(1)", "\u5165\u5c04\u538b\u529b\u5747\u65b9\u6839");
    model.component("comp1").variable("var4")
         .set("pout_rms", "sqrt(1/(T0)*timeint(4*T0,5*T0,intop_out(tatd.p_t)^2,1e-3,T0[1/s]/50))/intop_out(1)", "\u900f\u5c04\u538b\u529b\u5747\u65b9\u6839");
    model.component("comp1").variable("var4")
         .set("w_rms", "sqrt(1/(T0)*timeint(4*T0,5*T0,intop_mid(w2)^2,1e-3,T0[1/s]/50))/intop_mid(1)", "\u4e2d\u5fc3\u901f\u5ea6\u5747\u65b9\u6839");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_mid");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(8);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_in");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop2").selection().set(6, 15);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "intop_out");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop3").selection().set(10, 17);
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "intop_pnt");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop4").selection().set(11);
    model.component("comp1").cpl("intop4").set("axisym", false);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.component("comp1").material("mat1").propertyGroup()
         .create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.component("comp1").material("mat1").label("Air");
    model.component("comp1").material("mat1").set("family", "air");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.component("comp1").material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotfixedvalue", new String[]{"273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("argunit", new String[]{"Pa", "K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotaxis", new String[]{"off", "on"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.component("comp1").material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotfixedvalue", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup("def").func("an2")
         .set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.component("comp1").material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.component("comp1").material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("def").addInput("pressure");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.component("comp1").material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.component("comp1").material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.component("comp1").material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.component("comp1").material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.component("comp1").material("mat1").materialType("nonSolid");

    model.component("comp1").physics("ta").selection().set(2, 3);
    model.component("comp1").physics("ta").create("wall2", "Wall", 1);
    model.component("comp1").physics("ta").feature("wall2").selection().set(19, 20);
    model.component("comp1").physics("ta").feature("wall2").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("ta").feature("wall2").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("ta").create("port1", "Port", 1);
    model.component("comp1").physics("ta").feature("port1").selection().set(4);
    model.component("comp1").physics("ta").feature("port1").set("PortType", "PlaneWave");
    model.component("comp1").physics("ta").feature("port1").set("pamp", "pin");
    model.component("comp1").physics("ta").create("port2", "Port", 1);
    model.component("comp1").physics("ta").feature("port2").selection().set(12);
    model.component("comp1").physics("ta").feature("port2").set("PortType", "PlaneWave");
    model.component("comp1").physics("tatd").selection().set(2, 3);
    model.component("comp1").physics("tatd").prop("Stabilization").set("selStab", "GLSStab");
    model.component("comp1").physics("tatd").prop("TransientSettings").set("fmax", "f0");
    model.component("comp1").physics("tatd").create("wall2", "Wall", 1);
    model.component("comp1").physics("tatd").feature("wall2").selection().set(19, 20);
    model.component("comp1").physics("tatd").feature("wall2").set("MechanicalCondition", "Slip");
    model.component("comp1").physics("tatd").feature("wall2").set("ThermalCondition", "Adiabatic");
    model.component("comp1").physics("tatd").create("ntac1", "NonlinearThermoviscousAcousticsContributions", 2);
    model.component("comp1").physics("tatd").feature("ntac1").selection().set(2, 3);
    model.component("comp1").physics("actd").selection().set(1, 4);
    model.component("comp1").physics("actd").prop("TransientSettings").set("fmax", "f0");
    model.component("comp1").physics("actd").create("pwr1", "PlaneWaveRadiation", 1);
    model.component("comp1").physics("actd").feature("pwr1").selection().set(2, 13);
    model.component("comp1").physics("actd").feature("pwr1").create("ipf1", "IncidentPressureField", 1);
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("pamp", "pin");
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("c_mat", "from_mat");
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("PressureFieldMaterial", "mat1");
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("dir", new int[]{0, 0, 1});
    model.component("comp1").physics("actd").feature("pwr1").feature("ipf1").set("f0", "f0");

    model.component("comp1").multiphysics().create("atb1", "AcousticThermoacousticBoundary", 1);
    model.component("comp1").multiphysics("atb1").selection().set(4, 12);
    model.component("comp1").multiphysics("atb1").set("Thermoacoustics_physics", "tatd");

    model.component("comp1").mesh("mesh1").create("cr1", "CornerRefinement");
    model.component("comp1").mesh("mesh1").feature("cr1").selection("boundary").set(14, 15, 16, 17, 22, 23);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "a");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 4.8E-6);
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5, 6, 7, 8, 10);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "a/7");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(2, 3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "a/3");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").selection().set(14, 16, 22, 23);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size3").set("hmax", "dvisc");
    model.component("comp1").mesh("mesh1").create("bl1", "BndLayer");
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").selection().set(14, 15, 16, 17, 22, 23);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blnlayers", 3);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp").set("blhmin", "0.1*dvisc");

    return model;
  }

  public static Model run2(Model model) {
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76 1 - \u9891\u57df");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "a", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "ab", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.5 0.75 1", 0);
    model.study("std1").feature("freq").set("plist", "range(10,5,95) range(100,10,190) range(200,100,2000)");
    model.study("std1").feature("freq").setSolveFor("/physics/actd", false);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/atb1", false);
    model.study("std1").showAutoSequences("all");
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/ta", true);
    model.study("std2").feature("time").setSolveFor("/physics/tatd", true);
    model.study("std2").feature("time").setSolveFor("/physics/actd", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/atb1", true);
    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/ta", true);
    model.study("std3").feature("time").setSolveFor("/physics/tatd", true);
    model.study("std3").feature("time").setSolveFor("/physics/actd", true);
    model.study("std3").feature("time").setSolveFor("/multiphysics/atb1", true);
    model.study("std2").label("\u7814\u7a76 2 - \u65f6\u57df\u7ebf\u6027");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "a", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "ab", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.5 0.75 1", 0);
    model.study("std2").feature("param").setIndex("pname", "a", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "m", 1);
    model.study("std2").feature("param").setIndex("pname", "a", 1);
    model.study("std2").feature("param").setIndex("plistarr", "", 1);
    model.study("std2").feature("param").setIndex("punit", "m", 1);
    model.study("std2").feature("param").setIndex("pname", "f0", 1);
    model.study("std2").feature("param").setIndex("plistarr", "10 20 50 100 200 500 1000", 1);
    model.study("std2").feature("param").set("sweeptype", "filled");
    model.study("std2").feature("time").set("tlist", "range(0,T0,3*T0) range(4*T0,T0/50,5*T0)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"tatd/ntac1"});
    model.study("std2").showAutoSequences("all");
    model.study("std3").label("\u7814\u7a76 3 - \u65f6\u57df\u975e\u7ebf\u6027");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "a", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "a", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "ab", 0);
    model.study("std3").feature("param").setIndex("plistarr", "0.5 0.75 1", 0);
    model.study("std3").feature("param").setIndex("pname", "a", 1);
    model.study("std3").feature("param").setIndex("plistarr", "", 1);
    model.study("std3").feature("param").setIndex("punit", "m", 1);
    model.study("std3").feature("param").setIndex("pname", "a", 1);
    model.study("std3").feature("param").setIndex("plistarr", "", 1);
    model.study("std3").feature("param").setIndex("punit", "m", 1);
    model.study("std3").feature("param").setIndex("pname", "f0", 1);
    model.study("std3").feature("param").setIndex("plistarr", "10 20 50 100 200 500", 1);
    model.study("std3").feature("param").set("sweeptype", "filled");
    model.study("std3").feature("time").set("tlist", "range(0,T0,3*T0) range(4*T0,T0/50,5*T0)");
    model.study("std3").showAutoSequences("all");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.study("std2").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std2");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");
    model.batch("p2").run("compute");

    model.study("std3").createAutoSequences("all");

    model.sol().create("sol30");
    model.sol("sol30").study("std3");
    model.sol("sol30").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol30");
    model.batch("p3").run("compute");

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").label("\u4e8c\u7ef4\u955c\u50cf - \u9891\u7387");
    model.result().dataset("mir1").set("data", "dset4");
    model.result().dataset().create("mir2", "Mirror2D");
    model.result().dataset("mir2").label("\u4e8c\u7ef4\u955c\u50cf - \u7ebf\u6027");
    model.result().dataset("mir2").set("data", "dset5");
    model.result().dataset().create("mir3", "Mirror2D");
    model.result().dataset("mir3").label("\u4e8c\u7ef4\u955c\u50cf - \u975e\u7ebf\u6027");
    model.result().dataset("mir3").set("data", "dset6");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset6");
    model.result().dataset("rev1").set("revangle", 130);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("data", "dset4");
    model.result("pg1").setIndex("looplevel", 47, 0);
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 29, 0);
    model.result("pg1").set("edges", false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u58f0\u901f (ta)");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 47, 0);
    model.result("pg2").setIndex("looplevel", 3, 1);
    model.result("pg2").set("dataisaxisym", "off");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("expr", "ta.v_inst");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").label("\u58f0\u901f (ta)");
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 29, 0);
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").run();
    model.result("pg3").label("\u6e29\u5ea6 (ta)");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").setIndex("looplevel", 29, 0);
    model.result("pg3").set("edges", false);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "ta.T_t");
    model.result("pg3").feature("surf1").set("unit", "mK");
    model.result("pg3").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").label("T \u548c V (ta)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg4").setIndex("looplevel", 19, 0);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("edges", false);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("data", "mir1");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("expr", "ta.T_t");
    model.result("pg4").feature("surf1").set("unit", "mK");
    model.result("pg4").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg4").create("surf2", "Surface");
    model.result("pg4").feature("surf2").set("expr", "ta.v_inst");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("arrowxmethod", "coord");
    model.result("pg4").feature("arws1").set("xcoord", "range(0,0.06,0.6)");
    model.result("pg4").feature("arws1").set("arrowymethod", "coord");
    model.result("pg4").feature("arws1").set("ycoord", "range(-1.2,0.24,1.2)");
    model.result("pg4").feature("arws1").set("scaleactive", true);
    model.result("pg4").feature("arws1").set("scale", 0.15);
    model.result("pg4").feature("arws1").set("color", "white");
    model.result("pg4").feature("arws1").create("sel1", "Selection");
    model.result("pg4").feature("arws1").feature("sel1").selection().set(2, 3);
    model.result("pg4").create("line1", "Line");
    model.result("pg4").feature("line1").set("expr", "1");
    model.result("pg4").feature("line1").set("coloring", "uniform");
    model.result("pg4").feature("line1").set("color", "black");
    model.result("pg4").feature("line1").create("sel1", "Selection");
    model.result("pg4").feature("line1").feature("sel1").selection().set(3, 5, 7, 9, 14, 15, 16, 17, 22, 23);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u8f6c\u79fb\u963b\u6297\uff08\u9891\u57df\uff09");
    model.result("pg5").set("data", "none");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("xlog", true);
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").set("data", "dset4");
    model.result("pg5").feature("glob1").setIndex("looplevelinput", "last", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "real(Ztrans)", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "COMSOL, real(Z)", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "imag(Ztrans)", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "COMSOL, imag(Z)", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(Ztrans)", 2);
    model.result("pg5").feature("glob1").setIndex("descr", "COMSOL, abs(Z)", 2);
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").set("data", "dset1");
    model.result("pg5").feature("glob2").setIndex("expr", "real(Zlin)", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u89e3\u6790\uff0creal(Z)", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "imag(Zlin)", 1);
    model.result("pg5").feature("glob2").setIndex("descr", "\u89e3\u6790\uff0cimag(Z)", 1);
    model.result("pg5").feature("glob2").setIndex("expr", "abs(Zlin)", 2);
    model.result("pg5").feature("glob2").setIndex("descr", "\u89e3\u6790\uff0cabs(Z)", 2);
    model.result("pg5").feature("glob2").set("linestyle", "dashed");
    model.result("pg5").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8f6c\u79fb\u963b\u6297 vs. \u9891\u7387");
    model.result("pg6").set("data", "none");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "Z/\\rho c (1)");
    model.result("pg6").set("xlog", true);
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("data", "dset4");
    model.result("pg6").feature("glob1").setIndex("expr", "abs(Ztrans)", 0);
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg6").feature("glob1").set("linestyle", "cycle");
    model.result("pg6").feature("glob1").set("linecolor", "blue");
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legendsuffix", "\uff0c\u9891\u57df (COMSOL)");
    model.result("pg6").create("glob2", "Global");
    model.result("pg6").feature("glob2").set("markerpos", "datapoints");
    model.result("pg6").feature("glob2").set("linewidth", "preference");
    model.result("pg6").feature("glob2").set("data", "dset4");
    model.result("pg6").feature("glob2").setIndex("looplevelinput", "last", 1);
    model.result("pg6").feature("glob2").setIndex("expr", "abs(Zlin)", 0);
    model.result("pg6").feature("glob2").setIndex("descr", "\u7ebf\u6027\u89e3\u6790\u89e3", 0);
    model.result("pg6").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg6").feature("glob2").set("linecolor", "red");
    model.result("pg6").feature("glob2").set("linewidth", 2);
    model.result("pg6").create("glob3", "Global");
    model.result("pg6").feature("glob3").set("markerpos", "datapoints");
    model.result("pg6").feature("glob3").set("linewidth", "preference");
    model.result("pg6").feature("glob3").set("data", "dset4");
    model.result("pg6").feature("glob3").setIndex("looplevelinput", "last", 1);
    model.result("pg6").feature("glob3").setIndex("expr", "abs(Rnonlin+i*Dnonlin)", 0);
    model.result("pg6").feature("glob3").setIndex("descr", "\u975e\u7ebf\u6027\u89e3\u6790\u89e3", 0);
    model.result("pg6").feature("glob3").set("xdatasolnumtype", "level1");
    model.result("pg6").feature("glob3").set("linestyle", "dashdot");
    model.result("pg6").feature("glob3").set("linecolor", "red");
    model.result("pg6").feature("glob3").set("linewidth", 2);
    model.result("pg6").create("glob4", "Global");
    model.result("pg6").feature("glob4").set("markerpos", "datapoints");
    model.result("pg6").feature("glob4").set("linewidth", "preference");
    model.result("pg6").feature("glob4").set("data", "dset5");
    model.result("pg6").feature("glob4").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("glob4").setIndex("expr", "Ztime", 0);
    model.result("pg6").feature("glob4").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob4").set("xdata", "expr");
    model.result("pg6").feature("glob4").set("xdataexpr", "f0");
    model.result("pg6").feature("glob4").set("linestyle", "none");
    model.result("pg6").feature("glob4").set("linecolor", "custom");
    model.result("pg6").feature("glob4")
         .set("customlinecolor", new double[]{0.43921568989753723, 0.686274528503418, 0.10196078568696976});
    model.result("pg6").feature("glob4").set("linewidth", 2);
    model.result("pg6").feature("glob4").set("linemarker", "point");
    model.result("pg6").feature("glob4").set("legendmethod", "manual");
    model.result("pg6").feature("glob4").setIndex("legends", "\u7ebf\u6027\u77ac\u6001 (COMSOL)", 0);
    model.result("pg6").create("glob5", "Global");
    model.result("pg6").feature("glob5").set("markerpos", "datapoints");
    model.result("pg6").feature("glob5").set("linewidth", "preference");
    model.result("pg6").feature("glob5").set("data", "dset6");
    model.result("pg6").feature("glob5").setIndex("looplevelinput", "first", 2);
    model.result("pg6").feature("glob5").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("glob5").setIndex("expr", "Ztime", 0);
    model.result("pg6").feature("glob5").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob5").set("xdata", "expr");
    model.result("pg6").feature("glob5").set("xdataexpr", "f0");
    model.result("pg6").feature("glob5").set("linestyle", "none");
    model.result("pg6").feature("glob5").set("linecolor", "black");
    model.result("pg6").feature("glob5").set("linewidth", 2);
    model.result("pg6").feature("glob5").set("linemarker", "cycle");
    model.result("pg6").feature("glob5").set("legendmethod", "manual");
    model.result("pg6").feature("glob5")
         .setIndex("legends", "ab=0.5\uff0c\u975e\u7ebf\u6027\u77ac\u6001 (COMSOL)", 0);
    model.result("pg6").create("glob6", "Global");
    model.result("pg6").feature("glob6").set("markerpos", "datapoints");
    model.result("pg6").feature("glob6").set("linewidth", "preference");
    model.result("pg6").feature("glob6").set("data", "dset6");
    model.result("pg6").feature("glob6").setIndex("looplevelinput", "manual", 2);
    model.result("pg6").feature("glob6").setIndex("looplevel", new int[]{2}, 2);
    model.result("pg6").feature("glob6").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("glob6").setIndex("expr", "Ztime", 0);
    model.result("pg6").feature("glob6").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob6").set("xdata", "expr");
    model.result("pg6").feature("glob6").set("xdataexpr", "f0");
    model.result("pg6").feature("glob6").set("linestyle", "none");
    model.result("pg6").feature("glob6").set("linecolor", "black");
    model.result("pg6").feature("glob6").set("linewidth", 2);
    model.result("pg6").feature("glob6").set("linemarker", "cycle");
    model.result("pg6").feature("glob6").set("legendmethod", "manual");
    model.result("pg6").feature("glob6")
         .setIndex("legends", "ab=0.75\uff0c\u975e\u7ebf\u6027\u77ac\u6001 (COMSOL)", 0);
    model.result("pg6").create("glob7", "Global");
    model.result("pg6").feature("glob7").set("markerpos", "datapoints");
    model.result("pg6").feature("glob7").set("linewidth", "preference");
    model.result("pg6").feature("glob7").set("data", "dset6");
    model.result("pg6").feature("glob7").setIndex("looplevelinput", "last", 2);
    model.result("pg6").feature("glob7").setIndex("looplevelinput", "first", 0);
    model.result("pg6").feature("glob7").setIndex("expr", "Ztime", 0);
    model.result("pg6").feature("glob7").set("xdatasolnumtype", "outer");
    model.result("pg6").feature("glob7").set("xdata", "expr");
    model.result("pg6").feature("glob7").set("xdataexpr", "f0");
    model.result("pg6").feature("glob7").set("linestyle", "none");
    model.result("pg6").feature("glob7").set("linecolor", "black");
    model.result("pg6").feature("glob7").set("linewidth", 2);
    model.result("pg6").feature("glob7").set("linemarker", "cycle");
    model.result("pg6").feature("glob7").set("legendmethod", "manual");
    model.result("pg6").feature("glob7").setIndex("legends", "ab=1\uff0c\u975e\u7ebf\u6027\u77ac\u6001 (COMSOL)", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u8f6c\u79fb\u963b\u6297 vs. Strouhal \u6570");
    model.result("pg7").set("xlabel", "Sr (1)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("xdata", "expr");
    model.result("pg7").feature("glob1").set("xdataexpr", "Sr");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("xdata", "expr");
    model.result("pg7").feature("glob2").set("xdataexpr", "Sr");
    model.result("pg7").run();
    model.result("pg7").feature("glob3").set("xdata", "expr");
    model.result("pg7").feature("glob3").set("xdataexpr", "Sr");
    model.result("pg7").run();
    model.result("pg7").feature("glob4").set("xdataexpr", "Sr2");
    model.result("pg7").run();
    model.result("pg7").feature("glob5").set("xdataexpr", "Sr2");
    model.result("pg7").run();
    model.result("pg7").feature("glob6").set("xdataexpr", "Sr2");
    model.result("pg7").run();
    model.result("pg7").feature("glob7").set("xdataexpr", "Sr2");
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").run();
    model.result("pg8").label("\u58f0\u538b (tatd)");
    model.result("pg8").set("data", "mir3");
    model.result("pg8").set("looplevel", new String[]{"last", "3", "2"});
    model.result("pg8").set("titletype", "label");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "tatd.p_t");
    model.result("pg8").feature("surf1").set("colortable", "Wave");
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("expr", "actd.p_t");
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").run();
    model.result("pg9").label("T \u548c V (tatd)");
    model.result("pg9").set("data", "dset6");
    model.result("pg9").set("looplevel", new int[]{30, 4, 1});
    model.result("pg9").set("titletype", "label");
    model.result("pg9").set("edges", false);
    model.result("pg9").set("showlegendsunit", true);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("data", "mir3");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("expr", "tatd.T_t");
    model.result("pg9").feature("surf1").set("unit", "mK");
    model.result("pg9").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg9").create("surf2", "Surface");
    model.result("pg9").feature("surf2").set("expr", "tatd.v_inst");
    model.result("pg9").create("arws1", "ArrowSurface");
    model.result("pg9").feature("arws1").set("expr", new String[]{"tatd.u_tr", "tatd.u_tz"});
    model.result("pg9").feature("arws1").set("arrowxmethod", "coord");
    model.result("pg9").feature("arws1").set("xcoord", "range(0,0.06,0.6)");
    model.result("pg9").feature("arws1").set("arrowymethod", "coord");
    model.result("pg9").feature("arws1").set("ycoord", "range(-1.2,0.21333333333333335,2)");
    model.result("pg9").feature("arws1").set("color", "white");
    model.result("pg9").feature("arws1").create("sel1", "Selection");
    model.result("pg9").feature("arws1").feature("sel1").selection().set(2, 3);
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("expr", "1");
    model.result("pg9").feature("line1").set("coloring", "uniform");
    model.result("pg9").feature("line1").set("color", "black");
    model.result("pg9").feature("line1").create("sel1", "Selection");
    model.result("pg9").feature("line1").feature("sel1").selection().set(3, 5, 7, 9, 14, 15, 16, 17, 22, 23);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").run();
    model.result("pg10").label("\u58f0\u5b66\u6e29\u5ea6 (tatd)");
    model.result("pg10").set("data", "mir3");
    model.result("pg10").set("looplevel", new int[]{17, 2, 2});
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("edges", false);
    model.result("pg10").set("showlegendsunit", true);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "tatd.T_t");
    model.result("pg10").feature("surf1").set("unit", "mK");
    model.result("pg10").feature("surf1").set("colortable", "ThermalWave");
    model.result("pg10").run();
    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").run();
    model.result("pg11").label("\u4e09\u7ef4\u58f0\u901f");
    model.result("pg11").set("looplevel", new int[]{47, 4, 3});
    model.result("pg11").set("titletype", "label");
    model.result("pg11").set("edges", false);
    model.result("pg11").set("showlegendsunit", true);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", "tatd.v_inst");
    model.result("pg11").create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg11").feature("arws1").setIndex("expr", "tatd.u_tr", 0);
    model.result("pg11").feature("arws1").setIndex("expr", 0, 1);
    model.result("pg11").feature("arws1").set("expr", new String[]{"tatd.u_tr", "0", "tatd.u_tz"});
    model.result("pg11").feature("arws1").set("placement", "elements");
    model.result("pg11").feature("arws1").set("color", "white");
    model.result("pg11").run();
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u5b54\u4e2d\u7684\u538b\u529b");
    model.result("pg12").set("data", "none");
    model.result("pg12").set("titletype", "label");
    model.result("pg12").set("xlog", true);
    model.result("pg12").create("ptgr1", "PointGraph");
    model.result("pg12").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg12").feature("ptgr1").set("linewidth", "preference");
    model.result("pg12").feature("ptgr1").set("data", "dset5");
    model.result("pg12").feature("ptgr1").setIndex("looplevelinput", "first", 2);
    model.result("pg12").feature("ptgr1").setIndex("looplevelinput", "manual", 1);
    model.result("pg12").feature("ptgr1").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg12").feature("ptgr1").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg12").feature("ptgr1").setIndex("looplevelindices", "range(5,1,55)", 0);
    model.result("pg12").feature("ptgr1").selection().set(3, 4, 5);
    model.result("pg12").feature("ptgr1").set("expr", "tatd.p_t");
    model.result("pg12").feature("ptgr1").set("xdatasolnumtype", "level1");
    model.result("pg12").create("ptgr2", "PointGraph");
    model.result("pg12").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg12").feature("ptgr2").set("linewidth", "preference");
    model.result("pg12").feature("ptgr2").set("data", "dset6");
    model.result("pg12").feature("ptgr2").setIndex("looplevelinput", "first", 2);
    model.result("pg12").feature("ptgr2").setIndex("looplevelinput", "manual", 1);
    model.result("pg12").feature("ptgr2").setIndex("looplevel", new int[]{4}, 1);
    model.result("pg12").feature("ptgr2").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg12").feature("ptgr2").setIndex("looplevelindices", "range(5,1,55)", 0);
    model.result("pg12").feature("ptgr2").selection().set(3, 4, 5);
    model.result("pg12").feature("ptgr2").set("expr", "tatd.p_t");
    model.result("pg12").feature("ptgr2").set("xdatasolnumtype", "level1");
    model.result("pg12").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg12").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg12").run();
    model.result().create("pg13", "PlotGroup1D");
    model.result("pg13").run();
    model.result("pg13").label("Sh vs. Sr");
    model.result("pg13").set("titletype", "label");
    model.result("pg13").set("xlog", true);
    model.result("pg13").set("ylog", true);
    model.result("pg13").set("showlegends", false);
    model.result("pg13").create("glob1", "Global");
    model.result("pg13").feature("glob1").set("markerpos", "datapoints");
    model.result("pg13").feature("glob1").set("linewidth", "preference");
    model.result("pg13").feature("glob1").setIndex("expr", "Sh", 0);
    model.result("pg13").feature("glob1").set("xdata", "expr");
    model.result("pg13").feature("glob1").set("xdataexpr", "Sr");
    model.result("pg13").run();
    model.result("pg13").run();
    model.result().create("pg14", "PlotGroup3D");
    model.result("pg14").run();
    model.result("pg14").label("\u7f29\u7565\u56fe");
    model.result("pg14").set("looplevel", new int[]{35, 6, 1});
    model.result("pg14").set("edges", false);
    model.result("pg14").set("showlegendsunit", true);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("expr", "tatd.v_inst");
    model.result("pg14").feature("surf1").set("colortable", "Prism");

    model.view("view3").set("showgrid", false);

    model.result("pg14").create("arws1", "ArrowSurface");
    model.result("pg14").feature("arws1").set("revcoordsys", "cylindrical");
    model.result("pg14").feature("arws1").setIndex("expr", "tatd.u_tr", 0);
    model.result("pg14").feature("arws1").setIndex("expr", 0, 1);
    model.result("pg14").feature("arws1").set("expr", new String[]{"tatd.u_tr", "0", "tatd.u_tz"});
    model.result("pg14").feature("arws1").set("placement", "elements");
    model.result("pg14").feature("arws1").set("scaleactive", true);
    model.result("pg14").feature("arws1").set("scale", 0.12);
    model.result("pg14").feature("arws1").set("color", "white");
    model.result("pg14").run();
    model.result("pg4").run();

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg4").setIndex("looplevel", 2, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 3, 1);
    model.result("pg4").run();
    model.result("pg4").setIndex("looplevel", 1, 1);
    model.result("pg9").run();
    model.result("pg9").set("looplevel", new int[]{30, 4, 1});
    model.result("pg13").run();

    model.title("\u9525\u5f62\u5b54\u7684\u975e\u7ebf\u6027\u8f6c\u79fb\u963b\u6297");

    model
         .description("\u672c\u6a21\u578b\u5206\u6790\u7a7f\u5b54\u677f\u6216\u5fae\u7a7f\u5b54\u677f (MPP)\u7684\u4e00\u4e2a\u9525\u5f62\u5b54\u5355\u5143\u7684\u975e\u7ebf\u6027\u8f6c\u79fb\u963b\u6297\uff0c\u5e76\u5bf9\u7a7f\u5b54\u677f\u7684\u4e0d\u540c\u9525\u5ea6\u548c\u9891\u7387\u8303\u56f4\u8fdb\u884c\u5206\u6790\u3002\n\n\u672c\u4f8b\u4f7f\u7528\u70ed\u9ecf\u6027\u58f0\u5b66\uff0c\u9891\u57df \u63a5\u53e3\u8fdb\u884c\u9891\u57df\u7ebf\u6027\u5206\u6790\uff0c\u5e76\u4f7f\u7528\u70ed\u9ecf\u6027\u58f0\u5b66\uff0c\u77ac\u6001 \u63a5\u53e3\u548c\u975e\u7ebf\u6027\u70ed\u9ecf\u6027\u58f0\u5b66\u8d21\u732e \u7279\u5f81\u8fdb\u884c\u65f6\u57df\u975e\u7ebf\u6027\u5206\u6790\u3002\u6b64\u5916\uff0c\u8fd8\u5728\u65f6\u57df\u4e2d\u5bf9\u6240\u9009\u9891\u7387\u8fdb\u884c\u7ebf\u6027\u5206\u6790\u3002\n\n\u672c\u4f8b\u5c06\u7ed3\u679c\u4e0e\u7b80\u5355\u76f4\u5706\u67f1\u5f62\u7a7f\u5b54\u677f\u914d\u7f6e\uff08\u5706\u5f62\u5b54\uff09\u7684\u89e3\u6790\u548c\u534a\u89e3\u6790\u6a21\u578b\u8fdb\u884c\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();

    model.label("nonlinear_transfer_impedance.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}

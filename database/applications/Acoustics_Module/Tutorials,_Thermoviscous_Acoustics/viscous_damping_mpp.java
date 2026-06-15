/*
 * viscous_damping_mpp.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class viscous_damping_mpp {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Thermoviscous_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ta", "ThermoacousticsSinglePhysics", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ta", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Hslit", "1.5[\u00b5m]", "\u72ed\u7f1d\u9ad8\u5ea6");
    model.param().set("R", "2.3[\u00b5m]", "\u5b54\u534a\u5f84");
    model.param().set("H", "1.5[\u00b5m]", "\u819c\u539a\u5ea6");
    model.param().set("f0", "1[kHz]", "\u9a71\u52a8\u9891\u7387");
    model.param().set("AR", "0.2", "\u5b54\u9762\u79ef\u7cfb\u6570");
    model.param().set("W", "sqrt(pi/(2*sqrt(3))*R^2/AR)", "\u57fa\u672c\u5355\u5143\u7684\u5bbd\u5ea6");
    model.param().set("area", "2*sqrt(3)*W^2", "\u57fa\u672c\u5355\u5143\u7684\u9762\u79ef");
    model.param().set("N", "1/area", "\u5355\u4f4d\u9762\u79ef\u7684\u5b54\u6570");
    model.param().set("air_lambda", "6.5e-8[m]", "\u5e73\u5747\u81ea\u7531\u7a0b\uff0c\u7a7a\u6c14");
    model.param().set("Air_mu", "1.81e-5[Pa*s]", "\u9ecf\u5ea6\uff0c\u7a7a\u6c14");
    model.param().set("Air_rho", "1.2[kg/m^3]", "\u5bc6\u5ea6\uff0c\u7a7a\u6c14");
    model.param().set("Air_c", "343[m/s]", "\u58f0\u901f\uff0c\u7a7a\u6c14");
    model.param().set("Air_Dth", "22e-6[m^2/s]", "\u70ed\u6269\u6563\u7cfb\u6570\uff0c\u7a7a\u6c14");
    model.param()
         .set("Nopt", "sqrt(3/(2*H*Hslit^3)*(AR/2-AR^2/8-log(AR)/4-3/8))*AR/pi", "\u89e3\u6790\u6700\u4f73\u5b54\u6570");
    model.param().set("Ropt", "(AR/(pi*Nopt))^(1/2)", "\u89e3\u6790\u6700\u4f73\u534a\u5f84");
    model.param().set("delta_t", "sqrt(2*Air_Dth/f0)", "\u70ed\u8fb9\u754c\u5c42\u539a\u5ea6");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 30);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "W", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "W", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "tan(pi/6)*W", 2, 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Hslit", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Hslit+H", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Hslit+H+W", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Hslit+H+2*delta_t", 3);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("ext1", 6);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmd1", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd1").selection("input").set("fin", 1, 2, 3, 5, 6);
    model.component("comp1").geom("geom1").run("cmd1");
    model.component("comp1").geom("geom1").create("cmd2", "CompositeDomains");
    model.component("comp1").geom("geom1").feature("cmd2").selection("input").set("cmd1", 2, 3);
    model.component("comp1").geom("geom1").run("cmd2");

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

    model.component("comp1").physics("ta").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("ta").feature("sym1").selection().set(1, 2, 4, 5, 11, 12, 13);
    model.component("comp1").physics("ta").create("port1", "Port", 2);
    model.component("comp1").physics("ta").feature("port1").selection().set(7);
    model.component("comp1").physics("ta").feature("port1").set("PortExcitation", "off");
    model.component("comp1").physics("ta").feature("port1").set("PortType", "PlaneWave");
    model.component("comp1").physics("ta").feature("port1").set("MultiplicationFactor", "UserDefined");
    model.component("comp1").physics("ta").feature("port1").set("A_scale", 12);
    model.component("comp1").physics("ta").create("iso1", "Isothermal", 2);
    model.component("comp1").physics("ta").feature("iso1").selection().set(3);
    model.component("comp1").physics("ta").create("velt1", "VelocityThermoacoustic", 2);
    model.component("comp1").physics("ta").feature("velt1").selection().set(3);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 0);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 1);
    model.component("comp1").physics("ta").feature("velt1").setIndex("Direction", true, 2);
    model.component("comp1").physics("ta").feature("velt1").setIndex("u0", "1[nm/s]", 2);
    model.component("comp1").physics("ta").create("slw1", "SlipWall", 2);
    model.component("comp1").physics("ta").feature("slw1").label("\u6ed1\u79fb\u58c1 - \u56fa\u5b9a");
    model.component("comp1").physics("ta").feature("slw1").selection().set(8, 9, 10);
    model.component("comp1").physics("ta").create("slw2", "SlipWall", 2);
    model.component("comp1").physics("ta").feature("slw2").label("\u6ed1\u79fb\u58c1 - \u632f\u52a8");
    model.component("comp1").physics("ta").feature("slw2").set("MechanicalCondition", "MovingWall");
    model.component("comp1").physics("ta").feature("slw2").set("u_wall", new String[]{"0", "0", "1[nm/s]"});
    model.component("comp1").physics("ta").feature("slw2").selection().set(3);

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().set(3, 9, 11);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", "Hslit/3");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size2").set("hmax", "R/8");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 2);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(6);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("targetface").set(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 - \u6ed1\u79fb\u58c1");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Hslit", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "Hslit", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(1[\u00b5m],0.1[\u00b5m],4[\u00b5m])", 0);
    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u58f0\u538b (ta)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 31, 1);
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("colortable", "Wave");
    model.result("pg1").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u58f0\u901f (ta)");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 31, 1);
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").feature().create("slc1", "Slice");
    model.result("pg2").feature("slc1").label("\u5207\u9762");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg2").feature("slc1").set("smooth", "internal");
    model.result("pg2").feature("slc1").set("showsolutionparams", "on");
    model.result("pg2").feature("slc1").set("data", "parent");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u6e29\u5ea6\u53d8\u5316 (ta)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 31, 1);
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("expr", "ta.T_t");
    model.result("pg3").feature("mslc1").set("colortable", "ThermalWave");
    model.result("pg3").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("mslc1").set("smooth", "internal");
    model.result("pg3").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg3").feature("mslc1").set("data", "parent");

    model.component("comp1").geom("geom1").run();

    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/ta", true);
    model.study("std2").label("\u7814\u7a76 - \u65e0\u6ed1\u79fb");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Hslit", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "Hslit", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "R", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(1[\u00b5m],0.1[\u00b5m],4[\u00b5m])", 0);
    model.study("std2").feature("freq").set("plist", "f0");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"ta/slw1", "ta/slw2"});
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol35");
    model.sol("sol35").study("std2");
    model.sol("sol35").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol35");
    model.batch("p2").run("compute");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u58f0\u538b (ta) 1");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 1, 0);
    model.result("pg4").setIndex("looplevel", 31, 1);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("colortable", "Wave");
    model.result("pg4").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg4").feature("mslc1").set("smooth", "internal");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u58f0\u901f (ta) 1");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 31, 1);
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").feature().create("slc1", "Slice");
    model.result("pg5").feature("slc1").label("\u5207\u9762");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("expr", "ta.v_inst");
    model.result("pg5").feature("slc1").set("smooth", "internal");
    model.result("pg5").feature("slc1").set("showsolutionparams", "on");
    model.result("pg5").feature("slc1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6e29\u5ea6\u53d8\u5316 (ta) 1");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").setIndex("looplevel", 1, 0);
    model.result("pg6").setIndex("looplevel", 31, 1);
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").feature().create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("expr", "ta.T_t");
    model.result("pg6").feature("mslc1").set("colortable", "ThermalWave");
    model.result("pg6").feature("mslc1").set("colorscalemode", "linearsymmetric");
    model.result("pg6").feature("mslc1").set("smooth", "internal");
    model.result("pg6").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg6").feature("mslc1").set("data", "parent");
    model.result("pg4").run();
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(1);
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().geom("geom1", 3);
    model.result().dataset("dset4").selection().set(1);
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 - \u6ed1\u79fb\u58c1");
    model.result().evaluationGroup("eg1").create("av1", "AvSurface");
    model.result().evaluationGroup("eg1").feature("av1").set("intvolume", true);
    model.result().evaluationGroup("eg1").feature("av1").selection().set(3);
    model.result().evaluationGroup("eg1").feature("av1").setIndex("expr", "real(ta.p_t/(ta.rho0*ta.c)/w)", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("\u8ba1\u7b97\u7ec4 - \u65e0\u6ed1\u79fb");
    model.result().evaluationGroup("eg2").set("data", "dset4");
    model.result().evaluationGroup("eg2").create("av1", "AvSurface");
    model.result().evaluationGroup("eg2").feature("av1").set("intvolume", true);
    model.result().evaluationGroup("eg2").feature("av1").selection().set(3);
    model.result().evaluationGroup("eg2").feature("av1").setIndex("expr", "real(ta.p_t/(ta.rho0*ta.c)/w)", 0);
    model.result().evaluationGroup("eg2").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u963b\u6297 vs. R");
    model.result("pg7").create("tblp1", "Table");
    model.result("pg7").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg7").feature("tblp1").set("linewidth", "preference");
    model.result("pg7").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg7").feature("tblp1").set("xaxisdata", 1);
    model.result("pg7").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg7").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg7").feature("tblp1").set("linemarker", "circle");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "\u6ed1\u79fb\u58c1", 0);
    model.result("pg7").run();
    model.result("pg7").create("tblp2", "Table");
    model.result("pg7").feature("tblp2").set("markerpos", "datapoints");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg7").feature("tblp2").set("linewidth", "preference");
    model.result("pg7").feature("tblp2").set("source", "evaluationgroup");
    model.result("pg7").feature("tblp2").set("evaluationgroup", "eg2");
    model.result("pg7").feature("tblp2").set("xaxisdata", 1);
    model.result("pg7").feature("tblp2").set("plotcolumninput", "manual");
    model.result("pg7").feature("tblp2").set("plotcolumns", new int[]{3});
    model.result("pg7").feature("tblp2").set("linecolor", "green");
    model.result("pg7").feature("tblp2").set("linemarker", "circle");
    model.result("pg7").feature("tblp2").set("legend", true);
    model.result("pg7").feature("tblp2").set("legendmethod", "manual");
    model.result("pg7").feature("tblp2").setIndex("legends", "\u65e0\u6ed1\u79fb", 0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").label("\u58f0\u901f - \u6ed1\u79fb\u58c1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("edges", false);
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("data", "dset2");
    model.result("pg8").feature("surf1").setIndex("looplevel", 1, 1);
    model.result("pg8").feature("surf1").set("expr", "ta.v_inst");
    model.result("pg8").run();
    model.result("pg8").create("surf2", "Surface");
    model.result("pg8").feature("surf2").set("data", "dset2");
    model.result("pg8").feature("surf2").setIndex("looplevel", 14, 1);
    model.result("pg8").feature("surf2").set("expr", "ta.v_inst");
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").create("trn1", "Transformation");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("trn1").set("move", new int[]{6, 0, 0});
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").setIndex("looplevel", 31, 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("trn1").set("move", new int[]{15, 0, 0});
    model.result("pg8").run();
    model.result("pg8").create("ann1", "Annotation");
    model.result("pg8").feature("ann1").label("\u6ce8\u91ca R=1.0");
    model.result("pg8").feature("ann1").set("latexmarkup", true);
    model.result("pg8").feature("ann1").set("text", "$R=1.0\\; \\mathrm{\\mu m}$");
    model.result("pg8").feature("ann1").set("posxexpr", 1);
    model.result("pg8").feature("ann1").set("anchorpoint", "uppermiddle");
    model.result("pg8").feature("ann1").set("showpoint", false);
    model.result("pg8").feature().duplicate("ann2", "ann1");
    model.result("pg8").run();
    model.result("pg8").feature("ann2").label("\u6ce8\u91ca R=2.3");
    model.result("pg8").feature("ann2").set("text", "$R=2.3\\; \\mathrm{\\mu m}$");
    model.result("pg8").feature("ann2").set("posxexpr", 8);
    model.result("pg8").feature().duplicate("ann3", "ann2");
    model.result("pg8").run();
    model.result("pg8").feature("ann3").label("\u6ce8\u91ca R=4.0");
    model.result("pg8").feature("ann3").set("text", "$R=4.0\\; \\mathrm{\\mu m}$");
    model.result("pg8").feature("ann3").set("posxexpr", 19);
    model.result("pg8").run();
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").run();
    model.result("pg9").label("\u8017\u6563 - \u6ed1\u79fb\u58c1");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("expr", "ta.diss_tot");
    model.result("pg9").feature("surf1").set("rangecoloractive", true);
    model.result("pg9").feature("surf1").set("rangecolormax", "2E-9");
    model.result("pg9").feature("surf1").set("rangecolormin", 0);
    model.result("pg9").feature("surf1").set("colortable", "HeatCamera");
    model.result("pg9").run();
    model.result("pg9").feature("surf2").set("expr", "ta.diss_tot");
    model.result("pg9").run();
    model.result("pg9").feature("surf3").set("expr", "ta.diss_tot");
    model.result("pg9").run();
    model.result().dataset().create("sec1", "Sector3D");
    model.result().dataset("sec1").set("data", "dset2");
    model.result().dataset("sec1").set("sectors", 12);
    model.result().dataset("sec1").set("include", "manual");
    model.result().dataset("sec1").set("startsector", -2);
    model.result().dataset("sec1").set("sectorsinclude", 9);
    model.result().dataset("sec1").set("trans", "rotrefl");
    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").run();
    model.result("pg10").label("\u51e0\u4f55\u5f62\u72b6");
    model.result("pg10").set("data", "sec1");
    model.result("pg10").set("titletype", "none");
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("expr", "1");
    model.result("pg10").feature("surf1").set("coloring", "uniform");
    model.result("pg10").feature("surf1").set("color", "gray");
    model.result("pg10").run();
    model.result("pg8").run();

    model.title("\u5fae\u5b54\u677f\u5728\u6ed1\u79fb\u6d41\u72b6\u6001\u4e0b\u7684\u9ecf\u6ede\u963b\u5c3c");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5e26\u7a7f\u5b54\u677f\u7684\u5e73\u9762\u5fae\u673a\u68b0\u7ed3\u6784\u7684\u963b\u6297\uff0c\u63a2\u8ba8\u4e86\u5728\u9ad8\u514b\u52aa\u68ee\u6570\u6761\u4ef6\u4e0b\uff0c\u201c\u6ed1\u79fb\u58c1\u201d\u8fb9\u754c\u6761\u4ef6\u5728\u9891\u57df\u4e2d\u7684\u91cd\u8981\u6027\uff0c\u4ee5\u53ca\u7a7f\u5b54\u677f\u7684\u6700\u4f73\u5b54\u5927\u5c0f\u3002");

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
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();

    model.label("viscous_damping_mpp.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

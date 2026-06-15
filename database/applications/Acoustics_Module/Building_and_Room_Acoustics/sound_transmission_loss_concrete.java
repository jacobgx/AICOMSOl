/*
 * sound_transmission_loss_concrete.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:26 by COMSOL 6.3.0.290. */
public class sound_transmission_loss_concrete {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Building_and_Room_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("acpr", "PressureAcoustics", "geom1");
    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").multiphysics().create("asb1", "AcousticStructureBoundary", 2);
    model.component("comp1").multiphysics("asb1").set("Acoustics_physics", "acpr");
    model.component("comp1").multiphysics("asb1").set("Structure_physics", "solid");
    model.component("comp1").multiphysics("asb1").selection().all();

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/acpr", true);
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/asb1", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("T", "203[mm]", "\u6df7\u51dd\u571f\u5899\u539a\u5ea6");
    model.param().set("H", "4.37[m]", "\u6df7\u51dd\u571f\u5899\u9ad8\u5ea6");
    model.param().set("W", "2.84[m]", "\u6df7\u51dd\u571f\u5899\u5bbd\u5ea6");
    model.param().set("seed", "42", "\u968f\u673a\u51fd\u6570\u79cd\u5b50");
    model.param().set("A", "1[Pa]", "\u5e73\u9762\u6ce2\u632f\u5e45");
    model.param().set("N", "100", "\u968f\u673a\u6ce2\u6570\u603b\u548c");
    model.param().set("c0", "343.2[m/s]", "\u7a7a\u6c14\u4e2d\u7684\u58f0\u901f");
    model.param().set("rho0", "1.2[kg/m^3]", "\u7a7a\u6c14\u5bc6\u5ea6");
    model.param().set("fmax", "1200[Hz]", "\u6700\u5927\u7814\u7a76\u9891\u7387");
    model.param().set("m", "2275[kg/m^3]*T", "\u5899\u8868\u9762\u5bc6\u5ea6");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "sound_transmission_loss_concrete_measurement_data.txt");
    model.func("int1").setEntry("funcnames", "col2", "STL_typical");
    model.func("int1").importData();
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("argunit", "Hz", 0);
    model.func("int1").setIndex("fununit", "dB", 0);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"T", "W", "H"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"3*T", "W+4*T", "H+4*T"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"T", "-2*T", "-2*T"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("layer", "T", 0);
    model.component("comp1").geom("geom1").feature("blk2").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layerback", true);
    model.component("comp1").geom("geom1").feature("blk2").set("layertop", true);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("acpr").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
    model.component("comp1").physics("solid").selection().set(1);

    model.component("comp1").view("view1").set("renderwireframe", true);

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
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u6df7\u51dd\u571f");
    model.component("comp1").material("mat2").selection().set(1);
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"31.6e9"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"0.2"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"2275"});

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u6269\u6563\u573a");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1").set("theta", "acos(costheta_rnd(n,freq[1/Hz],2,seed))");
    model.component("comp1").variable("var1").set("phi", "phi_rnd(n,freq[1/Hz],1,seed)");
    model.component("comp1").variable("var1").set("phase", "phase_rnd(n,freq[1/Hz],3,seed)");
    model.component("comp1").variable("var1").set("k0", "2*pi*freq/c0", "\u7a7a\u6c14\u4e2d\u7684\u6ce2\u6570");
    model.component("comp1").variable("var1")
         .set("kx", "cos(theta)*k0", "\u5206\u6ce2\u77e2\u91cf\uff0cx \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("ky", "sin(theta)*cos(phi)*k0", "\u5206\u6ce2\u77e2\u91cf\uff0cy \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("kz", "sin(theta)*sin(phi)*k0", "\u5206\u6ce2\u77e2\u91cf\uff0cz \u5206\u91cf");
    model.component("comp1").variable("var1")
         .set("px_room", "A/sqrt(2*N)*sum(exp(i*phase)*exp(-i*(kx*x+ky*y+kz*z)),n,1,N)", "\u623f\u95f4\u6269\u6563\u538b\u529b\u573a\uff0c\u6b63 x \u65b9\u5411\u7684\u6ce2");
    model.component("comp1").variable("var1")
         .set("vx_room", "A/(acpr.omega*rho0)/sqrt(2*N)*sum(kx*exp(i*phase)*exp(-i*(kx*x+ky*y+kz*z)),n,1,N)", "\u623f\u95f4\u6269\u6563\u7c92\u5b50\u901f\u5ea6\u573a\uff0c\u6b63 x \u65b9\u5411\u7684\u6ce2");
    model.component("comp1").variable("var1")
         .set("p_room_sq", "2*abs(px_room)^2", "\u623f\u95f4\u6269\u6563\u573a\uff0c\u603b\u5e73\u65b9\u538b\u529b");
    model.component("comp1").variable("var1")
         .set("p_refl", "A/sqrt(2*N)*sum(exp(i*phase)*exp(-i*(-kx*x+ky*y+kz*z)),n,1,N)", "\u6df7\u51dd\u571f\u8868\u9762\u53cd\u5c04\u7684\u538b\u529b\u573a");
    model.component("comp1").variable("var1")
         .set("p_wall", "px_room+p_refl", "\u58c1\u603b\u538b\uff08\u673a\u68b0\u8f7d\u8377\uff09");
    model.component("comp1").variable().create("var2");
    model.component("comp1").variable("var2").label("\u53d8\u91cf\uff1aSTL");

//    To import content from file, use:
//    model.component("comp1").variable("var2").loadFile("FILENAME");
    model.component("comp1").variable("var2")
         .set("Ix_room", "0.5*realdot(px_room,vx_room)", "\u58f0\u5f3a\uff0c\u6b63 x \u65b9\u5411");
    model.component("comp1").variable("var2")
         .set("P_in", "intop_in(Ix_room)", "\u6df7\u51dd\u571f\u8868\u9762\u7684\u5165\u5c04\u529f\u7387");
    model.component("comp1").variable("var2")
         .set("P_in_proom", "intop_in(0.5*p_room_sq)/(4*rho0*c0)", "\u5165\u5c04\u529f\u7387\uff08\u623f\u95f4\u538b\u529b\uff09");
    model.component("comp1").variable("var2")
         .set("p_ms_th", "0.5*A^2", "\u58f0\u6e90\u5ba4\u7684\u7406\u8bba\u5747\u65b9\u538b\u529b\uff08\u6781\u9650 N->\u65e0\u7a77\u5927\uff09");
    model.component("comp1").variable("var2")
         .set("P_in_th", "intop_in(p_ms_th)/(4*rho0*c0)", "\u7406\u8bba\u5165\u5c04\u529f\u7387\uff08\u6781\u9650 N->\u65e0\u7a77\u5927\uff09");
    model.component("comp1").variable("var2").set("P_tr", "intop_tr(acpr.Ix)", "\u4f20\u8f93\u529f\u7387");

    model.component("comp1").func().create("rn1", "Random");
    model.component("comp1").func("rn1").set("funcname", "costheta_rnd");
    model.component("comp1").func("rn1").set("nargs", 4);
    model.component("comp1").func("rn1").set("mean", 0.5);
    model.component("comp1").func().create("rn2", "Random");
    model.component("comp1").func("rn2").set("funcname", "phi_rnd");
    model.component("comp1").func("rn2").set("nargs", 4);
    model.component("comp1").func("rn2").set("mean", "pi");
    model.component("comp1").func("rn2").set("uniformrange", "2*pi");
    model.component("comp1").func().create("rn3", "Random");
    model.component("comp1").func("rn3").set("funcname", "phase_rnd");
    model.component("comp1").func("rn3").set("nargs", 4);
    model.component("comp1").func("rn3").set("mean", "pi");
    model.component("comp1").func("rn3").set("uniformrange", "2*pi");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_in");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(1);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "intop_tr");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(26);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u4fdd\u5b58\u8fb9\u754c\u4e0a\u7684\u58f0\u5b66\u89e3");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(19, 26, 53);
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u4fdd\u5b58\u8fb9\u754c\u4e0a\u7684\u5b9e\u4f53\u89e3");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(1, 2, 3, 4, 5, 26);

    model.component("comp1").coordSystem().create("pml1", "PML");
    model.component("comp1").coordSystem("pml1").selection()
         .set(2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19);
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "3");

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");

    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", new String[]{"0.01"});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2, 3, 4, 5);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(1);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("solid").feature("bndl1").set("pressure", "p_wall");

    model.component("comp1").mesh("mesh1").create("fq1", "FreeQuad");
    model.component("comp1").mesh("mesh1").feature("fq1").selection().set(19);
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "c0/fmax/5");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "c0/fmax/6");
    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(6, 9, 12, 16, 22, 26, 27, 30, 33);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(16, 26, 35, 39);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").selection()
         .set(11, 12, 13, 14, 15, 16, 17, 18, 19);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis2").set("numelem", 8);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u9891\u7387\u5206\u6790");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("i2").active(true);

    model.study("std1").feature("freq")
         .set("plist", "{35.5, 37.5, 40, 42.5, 45, 47.5, 50, 53, 56, 60, 63, 67, 71, 75, 80, 85, 90, 95, 100, 106, 112, 118, 125, 132, 140, 150, 160, 170, 180, 190, 200, 212, 224, 236, 250, 265, 280, 300, 315, 335, 355, 375, 400, 425, 450, 475, 500, 530, 560, 600, 630, 670, 710, 750, 800, 850, 900, 950, 1e3, 1.06e3, 1.12e3, 1.18e3}");
    model.study("std1").feature("freq").setEntry("outputmap", "acpr", "selection");
    model.study("std1").feature("freq").setEntry("outputselectionmap", "acpr", "sel1");
    model.study("std1").feature("freq").setEntry("outputmap", "solid", "selection");
    model.study("std1").feature("freq").setEntry("outputselectionmap", "solid", "sel2");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 62, 0);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg1").feature("surf1").set("colortable", "Wave");
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").label("\u58f0\u538b (acpr)");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 62, 0);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"acpr.Lp_t"});
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").label("\u58f0\u538b\u7ea7 (acpr)");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").setIndex("looplevel", 62, 0);
    model.result("pg3").create("iso1", "Isosurface");
    model.result("pg3").feature("iso1").set("expr", new String[]{"acpr.p_t"});
    model.result("pg3").feature("iso1").set("number", "10");
    model.result("pg3").feature("iso1").set("colortable", "Wave");
    model.result("pg3").feature("iso1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").set("showlegendsunit", true);
    model.result("pg3").label("\u58f0\u538b\uff0c\u7b49\u503c\u9762 (acpr)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").setIndex("looplevel", 62, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg4").feature("vol1").set("threshold", "manual");
    model.result("pg4").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("vol1").set("colortable", "Rainbow");
    model.result("pg4").feature("vol1").set("colortabletrans", "none");
    model.result("pg4").feature("vol1").set("colorscalemode", "linear");
    model.result("pg4").feature("vol1").set("resolution", "custom");
    model.result("pg4").feature("vol1").set("refine", 2);
    model.result("pg4").feature("vol1").set("colortable", "Prism");
    model.result("pg4").feature("vol1").create("def", "Deform");
    model.result("pg4").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").feature("vol1").feature().remove("def");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u5165\u5c04\u5f3a\u5ea6");
    model.result("pg5").set("showlegendsunit", true);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "Ix_room");
    model.result("pg5").feature("surf1").set("colortable", "Rainbow");
    model.result("pg5").feature("surf1").set("colorscalemode", "linear");
    model.result("pg5").feature("surf1").create("sel1", "Selection");
    model.result("pg5").feature("surf1").feature("sel1").selection().set(1);
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").label("\u900f\u5c04\u5f3a\u5ea6");
    model.result("pg6").feature("surf1").set("expr", "acpr.Ix");
    model.result("pg6").feature("surf1").feature("sel1").selection().set(26);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset1");
    model.result("pg7").setIndex("looplevel", 62, 0);
    model.result("pg7").label("\u4f4d\u79fb (solid)");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").set("showlegends", true);
    model.result("pg7").create("vol1", "Volume");
    model.result("pg7").feature("vol1").set("expr", new String[]{"solid.disp"});
    model.result("pg7").feature("vol1").set("threshold", "manual");
    model.result("pg7").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg7").feature("vol1").set("colortable", "SpectrumLight");
    model.result("pg7").feature("vol1").set("colortabletrans", "none");
    model.result("pg7").feature("vol1").set("colorscalemode", "linear");
    model.result("pg7").feature("vol1").set("resolution", "custom");
    model.result("pg7").feature("vol1").set("refine", 2);
    model.result("pg7").feature("vol1").create("def", "Deform");
    model.result("pg7").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg7").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg7").label("\u4f4d\u79fb (solid)");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "f = eval(freq) Hz");
    model.result("pg7").set("edges", false);
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").feature().remove("vol1");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "solid.disp");
    model.result("pg7").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg7").feature("surf1").set("colorscalemode", "linear");
    model.result("pg7").feature("surf1").create("def1", "Deform");
    model.result("pg7").feature("surf1").create("filt1", "Filter");
    model.result("pg7").feature("surf1").feature("filt1").set("expr", "z>1.5[m]");
    model.result("pg7").create("surf2", "Surface");
    model.result("pg7").feature("surf2").create("sel1", "Selection");
    model.result("pg7").feature("surf2").feature("sel1").selection().set(53);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").label("STL\uff1aP_in/P_tr\uff08\u500d\u9891\u7a0b\uff09");
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u58f0\u4f20\u8f93\u635f\u8017\uff08\u500d\u9891\u7a0b\uff09");
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "f (Hz)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "STL (dB)");
    model.result("pg8").set("legendpos", "lowerleft");
    model.result("pg8").create("oct1", "OctaveBand");
    model.result("pg8").feature("oct1").set("quantity", "bandpower");
    model.result("pg8").feature("oct1").set("markerpos", "datapoints");
    model.result("pg8").feature("oct1").set("linewidth", "preference");
    model.result("pg8").feature("oct1").selection().geom("geom1");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg8").feature("oct1").set("exprtype", "power");
    model.result("pg8").feature("oct1").set("expr", "P_in");
    model.result("pg8").feature("oct1").set("powerref", "P_tr");
    model.result("pg8").feature("oct1").set("quantity", "continuous");
    model.result("pg8").feature().duplicate("oct2", "oct1");
    model.result("pg8").feature("oct2").set("quantity", "bandaveragepsd");
    model.result("pg8").feature("oct2").set("type", "outline");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("data", "dset1");
    model.result("pg8").feature("glob1").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").feature("glob1")
         .setIndex("looplevel", new int[]{19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62}, 0);
    model.result("pg8").feature("glob1").setIndex("expr", "STL_typical(freq)", 0);
    model.result("pg8").feature("glob1").setIndex("unit", "", 0);
    model.result("pg8").feature("glob1").setIndex("descr", "\u5178\u578b\u6d4b\u91cf\u503c", 0);
    model.result("pg8").run();
    model.result().duplicate("pg9", "pg8");
    model.result("pg9").label("STL\uff1aP_in/P_tr\uff081/3 \u500d\u9891\u7a0b\uff09");
    model.result("pg9").set("title", "\u58f0\u4f20\u8f93\u635f\u8017\uff081/3 \u500d\u9891\u7a0b\uff09");
    model.result("pg9").feature("oct2").set("bandtype", "octave3");
    model.result("pg9").create("glob2", "Global");
    model.result("pg9").feature("glob2").set("markerpos", "datapoints");
    model.result("pg9").feature("glob2").set("linewidth", "preference");
    model.result("pg9").feature("glob2").set("data", "dset1");
    model.result("pg9").feature("glob2").setIndex("looplevelinput", "manual", 0);
    model.result("pg9").feature("glob2")
         .setIndex("looplevel", new int[]{29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62}, 0);
    model.result("pg9").feature("glob2").setIndex("expr", "10*log10(1+(pi*freq*m/(rho0*c0))^2)-5.5", 0);
    model.result("pg9").feature("glob2")
         .setIndex("descr", "\u590f\u666e\u65b9\u7a0b\uff08\u8d28\u91cf\u5b9a\u5f8b\uff09", 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u5165\u5c04\u529f\u7387\uff08\u4e09\u79cd\u65b9\u6cd5\uff09");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u5165\u5c04\u529f\u7387\uff1a\u4e09\u79cd\u8ba1\u7b97\u65b9\u6cd5");
    model.result("pg10").set("xlabelactive", true);
    model.result("pg10").set("xlabel", "f (Hz)");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u529f\u7387\uff1aP<sub>in</sub> (W)");
    model.result("pg10").set("xlog", true);
    model.result("pg10").set("ylog", true);
    model.result("pg10").set("legendpos", "lowerright");
    model.result("pg10").create("glob1", "Global");
    model.result("pg10").feature("glob1").set("markerpos", "datapoints");
    model.result("pg10").feature("glob1").set("linewidth", "preference");
    model.result("pg10").feature("glob1").setIndex("expr", "P_in", 0);
    model.result("pg10").feature("glob1").setIndex("expr", "P_in_proom", 1);
    model.result("pg10").feature("glob1").setIndex("expr", "P_in_th", 2);
    model.result("pg10").run();

    model.study().create("std2");
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("solnum", "auto");
    model.study("std2").feature("eig").set("notsolnum", "auto");
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/acpr", false);
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").setSolveFor("/multiphysics/asb1", false);
    model.study("std2").label("\u7814\u7a76 2 - \u7279\u5f81\u9891\u7387\u5206\u6790");
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 3);
    model.study("std2").feature("eig").set("eigwhich", "lr");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg11", "PlotGroup3D");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").setIndex("looplevel", 1, 0);
    model.result("pg11").label("\u632f\u578b (solid)");
    model.result("pg11").set("showlegends", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg11").feature("surf1").set("threshold", "manual");
    model.result("pg11").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg11").feature("surf1").set("colortable", "Rainbow");
    model.result("pg11").feature("surf1").set("colortabletrans", "none");
    model.result("pg11").feature("surf1").set("colorscalemode", "linear");
    model.result("pg11").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg11").feature("surf1").create("def", "Deform");
    model.result("pg11").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg11").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std2EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std2EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std2EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 2 - \u7279\u5f81\u9891\u7387\u5206\u6790)");
    model.result().evaluationGroup("std2EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std2EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std2EvgFrq").run();
    model.result("pg5").setIndex("looplevel", 23, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 35, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 47, 0);
    model.result("pg5").run();
    model.result("pg5").setIndex("looplevel", 59, 0);
    model.result("pg5").run();
    model.result("pg6").setIndex("looplevel", 23, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 35, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 47, 0);
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevel", 59, 0);
    model.result("pg6").run();
    model.result("pg7").setIndex("looplevel", 23, 0);
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 35, 0);
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 47, 0);
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 59, 0);
    model.result("pg7").run();
    model.result("pg11").run();
    model.result("pg11").set("looplevel", new int[]{2});
    model.result("pg11").run();
    model.result("pg11").set("looplevel", new int[]{3});
    model.result("pg11").run();

    model.title("\u6df7\u51dd\u571f\u5899\u7684\u58f0\u4f20\u8f93\u635f\u8017");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6df7\u51dd\u571f\u5899\u7684\u58f0\u5b66\u5206\u6790\uff0c\u63d0\u51fa\u4e86\u4e00\u79cd\u5b9e\u7528\u4e14\u6709\u6548\u7684\u65b9\u6cd5\u6765\u8ba1\u7b97\u5efa\u7b51\u6784\u4ef6\u7684\u58f0\u4f20\u8f93\u635f\u8017 (STL)\u3002\u53ea\u8981\u5efa\u7b51\u6784\u4ef6\u5bf9\u58f0\u6e90\u4fa7\u7684\u58f0\u573a\u51e0\u4e4e\u6ca1\u6709\u5f71\u54cd\uff0c\u5176\u4e2d\u4f7f\u7528\u7684\u65b9\u6cd5\u5c31\u6709\u6548\u3002\u6b64\u65b9\u6cd5\u57fa\u4e8e\u8fd9\u6837\u4e00\u4e2a\u5047\u8bbe\uff1a\u58f0\u6e90\u4fa7\u4e3a\u7406\u60f3\u7684\u6269\u6563\u573a\uff0c\u6df7\u51dd\u571f\u5899\u63a5\u6536\u4fa7\u4e3a\u7406\u60f3\u7684\u6d88\u58f0\u672b\u7aef\u3002\u60a8\u53ef\u4ee5\u6839\u636e\u6b64\u6a21\u578b\u4f7f\u7528\u7684\u65b9\u6cd5\u63d0\u53d6\u4e0e\u5b9e\u9a8c\u65e0\u5173\u7684\u7406\u60f3 STL\u3002\u672c\u4f8b\u5c06\u5206\u6790\u7ed3\u679c\u4e0e\u53d1\u8868\u7684\u5b9e\u9a8c\u6570\u636e\u8fdb\u884c\u6bd4\u8f83\uff0c\u4e24\u8005\u9ad8\u5ea6\u543b\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("sound_transmission_loss_concrete.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

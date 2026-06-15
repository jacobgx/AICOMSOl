/*
 * ahmed_body.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class ahmed_body {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("spf", "TurbulentFlowkeps", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);

    model.param().set("L", "1.044[m]");
    model.param().descr("L", "\u4f53\u957f");
    model.param().set("D", "0.389[m]");
    model.param().descr("D", "\u4f53\u5bbd");
    model.param().set("H_body", "0.288[m]");
    model.param().descr("H_body", "\u4f53\u9ad8");
    model.param().set("Sl", "0.222[m]");
    model.param().descr("Sl", "\u659c\u9762\u957f\u5ea6");
    model.param().set("Sb", "H_body-Sl*sin(25[deg])");
    model.param().descr("Sb", "\u659c\u9762\u5e95\u90e8");
    model.param().set("Rl", "sqrt(Sl^2-(H_body-Sb)^2)");
    model.param().descr("Rl", "\u9876\u9762\u957f\u5ea6");
    model.param().set("Uin", "40[m/s]");
    model.param().descr("Uin", "\u5165\u53e3\u901f\u5ea6");
    model.param().set("rho0", "1.2[kg/m^3]");
    model.param().descr("rho0", "\u53c2\u8003\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "ahmed_body.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*L", "8*L", "2*L"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-L", "-2*L", "0"});
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"L", "8*L", "2*L"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-L", "-2*L", "0"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk2", "imp1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "2.2*L");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0.2*L", "-0.1*L"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("cyl1", 1, 3, 4);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("del1", "dif1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("uni1", 10, 16);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("del2", 22);
    model.component("comp1").geom("geom1").feature("wp1").set("origin", "vertexproj");
    model.component("comp1").geom("geom1").feature("wp1").selection("originvertex").set("del2", 27);
    model.component("comp1").geom("geom1").feature("wp1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "0 Sb Sb H_body H_body H_body H_body 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("y", "0 0 0 -Rl -Rl L L L");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "D/2", 0);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 36, 41, 46, 47);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("mcd1", "MeshControlDomains");
    model.component("comp1").geom("geom1").feature("mcd1").selection("input").set("ige1", 2);
    model.component("comp1").geom("geom1").run("mcd1");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").set("mcd1", 12);
    model.component("comp1").geom("geom1").run("mcf1");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "file");
    model.func("int1").set("filename", "ahmed_body_kin.txt");
    model.func("int1").importData();
    model.func("int1").setIndex("argunit", "m", 0);
    model.func("int1").setIndex("argunit", "m", 1);
    model.func("int1").setEntry("funcnames", "col3", "kin");
    model.func("int1").setIndex("fununit", "m^2/s^2", 0);

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u8f66\u4f53");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16);

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

    model.component("comp1").physics("spf").create("wallbc2", "WallBC", 2);
    model.component("comp1").physics("spf").feature("wallbc2").set("BoundaryCondition", "Slip");
    model.component("comp1").physics("spf").feature("wallbc2").selection().set(4, 17);
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 2);
    model.component("comp1").physics("spf").feature("sym1").selection().set(1);
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(2);
    model.component("comp1").physics("spf").feature("inl1").set("RANSVarOption", "SpecifyTurbulenceVariables");
    model.component("comp1").physics("spf").feature("inl1").set("k0", "kin(x,z)");
    model.component("comp1").physics("spf").feature("inl1")
         .set("ep0", "spf.C_mu*kin(x,z)^2*spf.rho/(10*1.814e-5[Pa*s])");
    model.component("comp1").physics("spf").feature("inl1").set("ComponentWise", "VelocityFieldComponentWise");
    model.component("comp1").physics("spf").feature("inl1").set("u0", new String[]{"0", "Uin", "0"});
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(12);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "Id");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16);
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "Is");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().set(10);
    model.component("comp1").cpl().create("intop3", "Integration");
    model.component("comp1").cpl("intop3").set("axisym", true);
    model.component("comp1").cpl("intop3").set("opname", "Ik");
    model.component("comp1").cpl("intop3").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop3").selection().set(5, 6, 7, 13);
    model.component("comp1").cpl().create("intop4", "Integration");
    model.component("comp1").cpl("intop4").set("axisym", true);
    model.component("comp1").cpl("intop4").set("opname", "Ib");
    model.component("comp1").cpl("intop4").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop4").selection().set(11);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("A", "Id(max(spf.nymesh,0))");
    model.component("comp1").variable("var1").descr("A", "\u6295\u5f71\u9762\u79ef");
    model.component("comp1").variable("var1").set("Cd", "2/(A*Uin^2*rho0)*Id(p*spf.nymesh)");
    model.component("comp1").variable("var1").descr("Cd", "\u603b\u66f3\u529b\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Cs", "2/(A*Uin^2*rho0)*Is(p*spf.nymesh)");
    model.component("comp1").variable("var1").descr("Cs", "\u659c\u9762\u538b\u529b\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Ck", "2/(A*Uin^2*rho0)*Ik(p*spf.nymesh)");
    model.component("comp1").variable("var1").descr("Ck", "\u524d\u7aef\u538b\u529b\u7cfb\u6570");
    model.component("comp1").variable("var1").set("Cb", "2/(A*Uin^2*rho0)*Ib(p*spf.nymesh)");
    model.component("comp1").variable("var1").descr("Cb", "\u5e95\u90e8\u538b\u529b\u7cfb\u6570");
    model.component("comp1").variable("var1")
         .set("Sf", "Id(spf.rho*spf.u_tau*((v-spf.nymesh*(u*spf.nxmesh+v*spf.nymesh+w*spf.nzmesh)))/spf.uPlus)");
    model.component("comp1").variable("var1").descr("Sf", "\u8868\u9762\u6469\u64e6\u529b");
    model.component("comp1").variable("var1").set("Csf", "2/(A*Uin^2*rho0)*Sf");
    model.component("comp1").variable("var1").descr("Csf", "\u8868\u9762\u6469\u64e6\u7cfb\u6570");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.0025);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 0.4);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.25);
    model.component("comp1").mesh("mesh1").feature("size1").selection().set(24, 26);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", 0.05);
    model.component("comp1").mesh("mesh1").feature("size2").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("size2").set("hmax", 0.035);
    model.component("comp1").mesh("mesh1").run("size2");
    model.component("comp1").mesh("mesh1").create("size3", "Size");
    model.component("comp1").mesh("mesh1").feature("size3").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size3").selection().set(10, 11);
    model.component("comp1").mesh("mesh1").feature("size3").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size3").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").create("size4", "Size");
    model.component("comp1").mesh("mesh1").feature("size4").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("size4").selection().set(5, 6, 7, 8, 9, 13, 16);
    model.component("comp1").mesh("mesh1").feature("size4").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size4").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size4").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").create("size5", "Size");
    model.component("comp1").mesh("mesh1").feature("size5").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size5").selection().set(35, 36);
    model.component("comp1").mesh("mesh1").feature("size5").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size5").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size5").set("hmax", 0.01);
    model.component("comp1").mesh("mesh1").feature("cr1").active(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hgrad", 1.03);
    model.component("comp1").mesh("mesh1").run("ftet1");
    model.component("comp1").mesh("mesh1").create("ftet2", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet2").selection().set(1);
    model.component("comp1").mesh("mesh1").run("ftet2");
    model.component("comp1").mesh("mesh1").feature("bl1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("bl1").set("trimminangle", 280);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp1").set("blhminfact", 1.5);
    model.component("comp1").mesh("mesh1").feature("bl1").create("blp2", "BndLayerProp");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").selection().set(5, 6, 7, 13);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blnlayers", 6);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blstretch", 1.25);
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("inittype", "blhmin");
    model.component("comp1").mesh("mesh1").feature("bl1").feature("blp2").set("blhmin", "5E-4");
    model.component("comp1").mesh("mesh1").run("bl1");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(2);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemcount", 28);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("elemratio", 6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("slc1", "Slice");
    model.result("pg1").feature("slc1").label("\u5207\u9762");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("smooth", "internal");
    model.result("pg1").feature("slc1").set("showsolutionparams", "on");
    model.result("pg1").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16, 17);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("data", "surf1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u8868\u9762");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("expr", "p");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u58c1\u5206\u8fa8\u7387 (spf)");
    model.result("pg3").set("data", "surf1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u58c1\u5206\u8fa8\u7387");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf.Delta_wPlus");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().setOnlyPlotWhenRequested(true);
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result().dataset().create("surf2", "Surface");
    model.result().dataset("surf2").selection().named("sel1");
    model.result().dataset("surf2").selection().set(1, 3, 5, 6, 7, 8, 9, 10, 11, 13, 14, 15, 16);
    model.result().dataset("surf2").label("\u7ed8\u56fe\u8868\u9762");
    model.result("pg1").feature("slc1").set("quickxmethod", "coord");
    model.result("pg1").feature("slc1").set("quickx", 0.15);
    model.result("pg1").feature("slc1").set("colortable", "Disco");
    model.result("pg1").feature("slc1").set("colortabletrans", "reverse");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("data", "surf2");
    model.result("pg1").feature("surf1").set("expr", "1");
    model.result("pg1").feature("surf1").set("coloring", "uniform");
    model.result("pg1").feature("surf1").set("color", "gray");
    model.result("pg1").set("view", "new");
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "Cd", 0);
    model.result().numerical("gev1").setIndex("expr", "Ck", 1);
    model.result().numerical("gev1").setIndex("expr", "Cs", 2);
    model.result().numerical("gev1").setIndex("expr", "Cb", 3);
    model.result().numerical("gev1").setIndex("expr", "Csf", 4);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().dataset().create("surf3", "Surface");
    model.result().dataset("surf3").label("\u5bf9\u79f0\u5e73\u9762");
    model.result().dataset("surf3").set("param", "yz");
    model.result().dataset("surf3").selection().set(1);
    model.result().create("pg4", "PlotGroup2D");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").label("\u4e8c\u7ef4\u6d41\u7ebf");
    model.result("pg4").set("data", "surf3");
    model.result("pg4").create("str1", "Streamline");
    model.result("pg4").feature("str1").set("expr", new String[]{"v", "w"});
    model.result("pg4").feature("str1").set("number", 34);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u4e09\u7ef4\u6d41\u7ebf");
    model.result("pg5").set("edges", false);
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("data", "surf2");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").create("str1", "Streamline");
    model.result("pg5").feature("str1").set("posmethod", "start");
    model.result("pg5").feature("str1").set("startmethod", "coord");
    model.result("pg5").feature("str1")
         .set("xcoord", "range(0.01,0.03,0.16) range(0.01,0.03,0.16) range(0.01,0.03,0.16) range(0.01,0.03,0.16) range(0.01,0.03,0.16)");
    model.result("pg5").feature("str1").set("ycoord", "-0.5*L");
    model.result("pg5").feature("str1")
         .set("zcoord", "0.02*1^range(1,6) 0.08*1^range(1,6) 0.14*1^range(1,6)  0.2*1^range(1,6)  0.26*1^range(1,6)");
    model.result("pg5").feature("str1").set("linetype", "tube");
    model.result("pg5").feature("str1").set("radiusexpr", "k*1[s^2/m]");
    model.result("pg5").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature("str1").set("tuberadiusscale", "3e-4");
    model.result("pg5").feature("str1").create("col1", "Color");
    model.result("pg5").set("view", "new");
    model.result("pg5").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").label("\u622a\u9762 80\u00a0mm");
    model.result().dataset("cpl1").set("quickplane", "zx");
    model.result().dataset("cpl1").set("quicky", "L+0.08");
    model.result().dataset().create("cpl2", "CutPlane");
    model.result().dataset("cpl2").label("\u622a\u9762 200\u00a0mm");
    model.result().dataset("cpl2").set("quickplane", "zx");
    model.result().dataset("cpl2").set("quicky", "L+0.2");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6d41\u7ebf\uff1axz \u5e73\u9762\u4e2d\u7684\u901f\u5ea6\uff0c80\u00a0mm");
    model.result("pg6").create("str1", "StreamlineSurface");
    model.result("pg6").feature("str1").set("data", "cpl1");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.04);
    model.result("pg6").feature("str1").set("linetype", "tube");
    model.result("pg6").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg6").feature("str1").set("tuberadiusscale", "0.0005");
    model.result("pg6").feature("str1").set("pointtype", "arrow");
    model.result("pg6").feature("str1").set("arrowcountactive", true);
    model.result("pg6").feature("str1").set("arrowcount", 50);
    model.result("pg6").feature("str1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("str1").set("logrange", 1000);
    model.result("pg6").feature("str1").set("arrowscaleactive", true);
    model.result("pg6").feature("str1").set("arrowscale", 0.0015);
    model.result("pg6").feature("str1").set("color", "black");
    model.result("pg6").feature("str1").create("filt1", "Filter");
    model.result("pg6").feature("str1").feature("filt1").set("expr", "(x<0.35)*(z<0.45)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("data", "surf2");
    model.result("pg6").feature("surf1").set("expr", "1");
    model.result("pg6").feature("surf1").set("coloring", "uniform");
    model.result("pg6").feature("surf1").set("color", "gray");
    model.result("pg6").set("view", "new");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").label("\u6d41\u7ebf\uff1axz \u5e73\u9762\u4e2d\u7684\u901f\u5ea6\uff0c200\u00a0mm");
    model.result("pg7").feature("str1").set("data", "cpl2");
    model.result("pg7").run();

    model.title("Ahmed \u7c7b\u8f66\u4f53\u4e0a\u65b9\u7684\u6c14\u6d41");

    model
         .description("\u672c\u4f8b\u63cf\u8ff0\u5982\u4f55\u4f7f\u7528\u201cCFD \u6a21\u5757\u201d\u4e2d\u7684\u201c\u6e4d\u6d41\uff0ck-\u03b5\u201d\u63a5\u53e3\u8ba1\u7b97\u6c7d\u8f66\u72b6\u51e0\u4f55\u5468\u56f4\u7684\u6e4d\u6d41\u573a\u3002\u4eff\u771f\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u6570\u636e\u8fdb\u884c\u4e86\u6bd4\u8f83\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("ahmed_body.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

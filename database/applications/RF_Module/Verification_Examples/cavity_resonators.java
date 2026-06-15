/*
 * cavity_resonators.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:44 by COMSOL 6.3.0.290. */
public class cavity_resonators {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("emw", "ElectromagneticWaves", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/emw", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param()
         .set("eta", "sqrt(mu0_const/epsilon0_const)", "\u7279\u6027\u963b\u6297\uff0c\u81ea\u7531\u7a7a\u95f4");
    model.param().set("sigma_wall", "5.7e7[S/m]", "\u7535\u5bfc\u7387\uff0c\u8154\u58c1");
    model.param().set("d_f", "1", "\u79bb\u6563\u5316\u56e0\u5b50");
    model.param().set("a_r", "0.9[in]", "\u77e9\u5f62\u8154\u7684\u5bbd\u5ea6\u548c\u6df1\u5ea6");
    model.param().set("b_r", "0.4[in]", "\u77e9\u5f62\u8154\u9ad8\u5ea6");
    model.param().set("h_max_r", "a_r", "\u77e9\u5f62\u8154\u7684\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param()
         .set("f_TE101_analytic_r", "sqrt(2)/(2*a_r*sqrt(mu0_const*epsilon0_const))", "\u8c10\u632f\u9891\u7387\uff0c\u77e9\u5f62\u8154\u7684\u89e3\u6790 TE101");
    model.param()
         .set("Q_TE101_analytic_r", "1.1107*(eta/sqrt(2*pi*f_TE101_analytic_r*mu0_const/(2*sigma_wall)))*(1/(1+((a_r/2)/b_r)))", "Q \u56e0\u5b50\uff0c\u89e3\u6790\uff0c\u6a21\u5f0f\u76f8\u5173\uff0c\u77e9\u5f62\u8154");
    model.param().set("a_c", "0.48[in]", "\u5706\u67f1\u8154\u534a\u5f84");
    model.param().set("height_c", "0.4[in]", "\u5706\u67f1\u8154\u9ad8\u5ea6");
    model.param().set("h_max_c", "2*a_c", "\u5706\u67f1\u8154\u7684\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param()
         .set("f_TM010_analytic_c", "(1/(2*pi*sqrt(mu0_const*epsilon0_const)))*(2.4049/a_c)", "\u8c10\u632f\u9891\u7387\uff0c\u5706\u67f1\u8154\u7684\u89e3\u6790 TM010");
    model.param()
         .set("Q_TM010_analytic_c", "1.2025*(eta/sqrt(2*pi*f_TM010_analytic_c*mu0_const/(2*sigma_wall)))*(1/(1+(a_c/height_c)))", "Q \u56e0\u5b50\uff0c\u89e3\u6790\uff0c\u6a21\u5f0f\u76f8\u5173\uff0c\u5706\u67f1\u8154");
    model.param().set("a_s", "1.35[cm]", "\u7403\u5f62\u8154\u534a\u5f84");
    model.param().set("h_max_s", "2*a_s", "\u7403\u5f62\u8154\u7684\u6700\u5927\u5355\u5143\u5927\u5c0f");
    model.param()
         .set("f_TM011_analytic_s", "2.744/(2*pi*a_s*sqrt(epsilon0_const*mu0_const))", "\u8c10\u632f\u9891\u7387\uff0c\u7403\u5f62\u8154\u7684\u89e3\u6790 TM011");
    model.param()
         .set("Q_TM011_analytic_s", "1.004*eta/sqrt(2*pi*f_TM011_analytic_s*mu0_const/(2*sigma_wall))", "Q \u56e0\u5b50\uff0c\u89e3\u6790\uff0c\u6a21\u5f0f\u76f8\u5173\uff0c\u7403\u5f62\u8154");

    model.material().create("mat1", "Common", "");
    model.material("mat1").propertyGroup("def").func().create("eta", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("Cp", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("rho", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("k", "Piecewise");
    model.material("mat1").propertyGroup("def").func().create("cs", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an1", "Analytic");
    model.material("mat1").propertyGroup("def").func().create("an2", "Analytic");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive index");
    model.material("mat1").propertyGroup().create("NonlinearModel", "NonlinearModel", "Nonlinear model");
    model.material("mat1").propertyGroup().create("idealGas", "idealGas", "Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func().create("Cp", "Piecewise");
    model.material("mat1").label("Air");
    model.material("mat1").set("family", "air");
    model.material("mat1").propertyGroup("def").label("Basic");
    model.material("mat1").propertyGroup("def").func("eta").label("Piecewise");
    model.material("mat1").propertyGroup("def").func("eta").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("eta")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-8.38278E-7+8.35717342E-8*T^1-7.69429583E-11*T^2+4.6437266E-14*T^3-1.06585607E-17*T^4"}});
    model.material("mat1").propertyGroup("def").func("eta").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("eta").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("def").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("def").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("def").func("rho").label("Analytic");
    model.material("mat1").propertyGroup("def").func("rho").set("expr", "pA*0.02897/R_const[K*mol/J]/T");
    model.material("mat1").propertyGroup("def").func("rho").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("rho").set("fununit", "kg/m^3");
    model.material("mat1").propertyGroup("def").func("rho").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("rho").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("rho")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "293.15"}});
    model.material("mat1").propertyGroup("def").func("k").label("Piecewise 3");
    model.material("mat1").propertyGroup("def").func("k").set("arg", "T");
    model.material("mat1").propertyGroup("def").func("k")
         .set("pieces", new String[][]{{"200.0", "1600.0", "-0.00227583562+1.15480022E-4*T^1-7.90252856E-8*T^2+4.11702505E-11*T^3-7.43864331E-15*T^4"}});
    model.material("mat1").propertyGroup("def").func("k").set("argunit", "K");
    model.material("mat1").propertyGroup("def").func("k").set("fununit", "W/(m*K)");
    model.material("mat1").propertyGroup("def").func("cs").label("Analytic 2");
    model.material("mat1").propertyGroup("def").func("cs").set("expr", "sqrt(1.4*R_const[K*mol/J]/0.02897*T)");
    model.material("mat1").propertyGroup("def").func("cs").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("cs").set("fununit", "m/s");
    model.material("mat1").propertyGroup("def").func("cs").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("cs").set("plotfixedvalue", new String[]{"273.15"});
    model.material("mat1").propertyGroup("def").func("cs")
         .set("plotargs", new String[][]{{"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an1").label("Analytic 1");
    model.material("mat1").propertyGroup("def").func("an1").set("funcname", "alpha_p");
    model.material("mat1").propertyGroup("def").func("an1").set("expr", "-1/rho(pA,T)*d(rho(pA,T),T)");
    model.material("mat1").propertyGroup("def").func("an1").set("args", new String[]{"pA", "T"});
    model.material("mat1").propertyGroup("def").func("an1").set("fununit", "1/K");
    model.material("mat1").propertyGroup("def").func("an1").set("argunit", new String[]{"Pa", "K"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotaxis", new String[]{"off", "on"});
    model.material("mat1").propertyGroup("def").func("an1").set("plotfixedvalue", new String[]{"101325", "273.15"});
    model.material("mat1").propertyGroup("def").func("an1")
         .set("plotargs", new String[][]{{"pA", "101325", "101325"}, {"T", "273.15", "373.15"}});
    model.material("mat1").propertyGroup("def").func("an2").label("Analytic 2a");
    model.material("mat1").propertyGroup("def").func("an2").set("funcname", "muB");
    model.material("mat1").propertyGroup("def").func("an2").set("expr", "0.6*eta(T)");
    model.material("mat1").propertyGroup("def").func("an2").set("args", new String[]{"T"});
    model.material("mat1").propertyGroup("def").func("an2").set("fununit", "Pa*s");
    model.material("mat1").propertyGroup("def").func("an2").set("argunit", new String[]{"K"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotfixedvalue", new String[]{"200"});
    model.material("mat1").propertyGroup("def").func("an2").set("plotargs", new String[][]{{"T", "200", "1600"}});
    model.material("mat1").propertyGroup("def").set("thermalexpansioncoefficient", "");
    model.material("mat1").propertyGroup("def").set("molarmass", "");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "");
    model.material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)", "0", "0", "0", "alpha_p(pA,T)"});
    model.material("mat1").propertyGroup("def").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("def").set("bulkviscosity", "muB(T)");
    model.material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("def").set("dynamicviscosity", "eta(T)");
    model.material("mat1").propertyGroup("def").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.material("mat1").propertyGroup("def").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("def").set("density", "rho(pA,T)");
    model.material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k(T)", "0", "0", "0", "k(T)", "0", "0", "0", "k(T)"});
    model.material("mat1").propertyGroup("def").set("soundspeed", "cs(T)");
    model.material("mat1").propertyGroup("def").addInput("temperature");
    model.material("mat1").propertyGroup("def").addInput("pressure");
    model.material("mat1").propertyGroup("RefractiveIndex").label("Refractive index");
    model.material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.material("mat1").propertyGroup("NonlinearModel").label("Nonlinear model");
    model.material("mat1").propertyGroup("NonlinearModel").set("BA", "def.gamma-1");
    model.material("mat1").propertyGroup("idealGas").label("Ideal gas");
    model.material("mat1").propertyGroup("idealGas").func("Cp").label("Piecewise 2");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("arg", "T");
    model.material("mat1").propertyGroup("idealGas").func("Cp")
         .set("pieces", new String[][]{{"200.0", "1600.0", "1047.63657-0.372589265*T^1+9.45304214E-4*T^2-6.02409443E-7*T^3+1.2858961E-10*T^4"}});
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("argunit", "K");
    model.material("mat1").propertyGroup("idealGas").func("Cp").set("fununit", "J/(kg*K)");
    model.material("mat1").propertyGroup("idealGas").set("Rs", "R_const/Mn");
    model.material("mat1").propertyGroup("idealGas").set("heatcapacity", "Cp(T)");
    model.material("mat1").propertyGroup("idealGas").set("ratioofspecificheat", "1.4");
    model.material("mat1").propertyGroup("idealGas").set("molarmass", "0.02897[kg/mol]");
    model.material("mat1").propertyGroup("idealGas").addInput("temperature");
    model.material("mat1").propertyGroup("idealGas").addInput("pressure");
    model.material("mat1").materialType("nonSolid");
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("\u6709\u635f\u58c1");
    model.material("mat2").propertyGroup("def").set("relpermittivity", "");
    model.material("mat2").propertyGroup("def").set("relpermeability", "");
    model.material("mat2").propertyGroup("def").set("electricconductivity", "");
    model.material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});
    model.material("mat2").propertyGroup("def").set("relpermeability", new String[]{"1"});
    model.material("mat2").propertyGroup("def").set("electricconductivity", new String[]{"sigma_wall"});

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"a_r", "a_r", "b_r"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("emw").create("imp1", "Impedance", 2);
    model.component("comp1").physics("emw").feature("imp1").selection().all();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().geom("geom1", 2);
    model.component("comp1").material("matlnk2").selection().all();
    model.component("comp1").material("matlnk2").set("link", "mat2");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "int_v");
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("intop2", "Integration");
    model.component("comp1").cpl("intop2").set("axisym", true);
    model.component("comp1").cpl("intop2").set("opname", "int_s");
    model.component("comp1").cpl("intop2").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop2").selection().all();

    model.component("comp1").variable().create("var1");

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("W_t", "int_v(emw.Wav)", "\u5b58\u50a8\u7684\u5e73\u5747\u80fd\u91cf");
    model.component("comp1").variable("var1").set("P_d", "int_s(emw.Qsh)", "\u8017\u6563\u529f\u7387");
    model.component("comp1").variable("var1")
         .set("Q_definition", "2*pi*emw.freq*W_t/P_d", "Q \u56e0\u5b50\uff0c\u5b9a\u4e49");
    model.component("comp1").variable("var1")
         .set("Q_computed", "emw.Qfactor", "Q \u56e0\u5b50\uff0c\u57fa\u4e8e\u7279\u5f81\u503c\u8ba1\u7b97\u5f97\u5230");
    model.component("comp1").variable("var1").set("frequency", "emw.freq", "\u9891\u7387\uff0c\u6a21\u62df\u503c");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "h_max_r/d_f");
    model.component("comp1").mesh("mesh1").feature("size").set("hgrad", 2);
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hnarrow", 0.1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").feature("eig").set("shift", "9[GHz]");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "eta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "\u03a9", 0);
    model.study("std1").feature("param").setIndex("pname", "eta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "\u03a9", 0);
    model.study("std1").feature("param").setIndex("pname", "d_f", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1 2 4 8", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (emw)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg1").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("\u7279\u5f81\u9891\u7387 (emw)");
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").set("expr", new String[]{"emw.freq", "emw.Qfactor"});
    model.result().numerical("gev1").set("unit", new String[]{"GHz", "1"});
    model.result().table().create("tbl1", "Table");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").run();
    model.result().numerical("gev1").setResult();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"emw.Ex", "emw.Ey", "emw.Ez"});
    model.result("pg1").feature("arwv1").set("descr", "\u7535\u573a");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv2", "ArrowVolume");
    model.result("pg1").feature("arwv2").set("expr", new String[]{"emw.Hx", "emw.Hy", "emw.Hz"});
    model.result("pg1").feature("arwv2").set("descr", "\u78c1\u573a");
    model.result("pg1").feature("arwv2").set("znumber", 1);
    model.result("pg1").feature("arwv2").set("color", "white");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "emw.Qsh");
    model.result("pg2").feature("surf1").set("descr", "\u8868\u9762\u635f\u8017");
    model.result("pg2").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg2").run();
    model.result("pg2").label("\u8868\u9762\u635f\u8017 (emw)");
    model.result("pg2").create("arws1", "ArrowSurface");
    model.result("pg2").feature("arws1").set("expr", new String[]{"emw.Jsx", "emw.Jsy", "emw.Jsz"});
    model.result("pg2").feature("arws1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("arws1").set("color", "blue");
    model.result("pg2").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").physics().create("emw2", "ElectromagneticWaves", "geom2");

    model.study("std1").feature("eig").setSolveFor("/physics/emw2", true);

    model.component("comp2").geom("geom2").run();

    model.study().create("std2");

    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "a_c");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "height_c");
    model.component("comp2").geom("geom2").runPre("fin");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("emw2").create("imp1", "Impedance", 2);
    model.component("comp2").physics("emw2").feature("imp1").selection().all();

    model.component("comp2").material().create("matlnk3", "Link");
    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").selection().geom("geom2", 2);
    model.component("comp2").material("matlnk4").selection().all();
    model.component("comp2").material("matlnk4").set("link", "mat2");

    model.component("comp2").cpl().create("intop3", "Integration");
    model.component("comp2").cpl("intop3").set("axisym", true);
    model.component("comp2").cpl("intop3").set("opname", "int_v");
    model.component("comp2").cpl("intop3").selection().all();
    model.component("comp2").cpl().create("intop4", "Integration");
    model.component("comp2").cpl("intop4").set("axisym", true);
    model.component("comp2").cpl("intop4").set("opname", "int_s");
    model.component("comp2").cpl("intop4").selection().geom("geom2", 2);
    model.component("comp2").cpl("intop4").selection().all();

    model.component("comp2").variable().create("var2");

//    To import content from file, use:
//    model.component("comp2").variable("var2").loadFile("FILENAME");
    model.component("comp2").variable("var2")
         .set("W_t", "int_v(emw2.Wav)", "\u5b58\u50a8\u7684\u5e73\u5747\u80fd\u91cf");
    model.component("comp2").variable("var2").set("P_d", "int_s(emw2.Qsh)", "\u8017\u6563\u529f\u7387");
    model.component("comp2").variable("var2")
         .set("Q_definition", "2*pi*emw2.freq*W_t/P_d", "Q \u56e0\u5b50\uff0c\u5b9a\u4e49");
    model.component("comp2").variable("var2")
         .set("Q_computed", "emw2.Qfactor", "Q \u56e0\u5b50\uff0c\u57fa\u4e8e\u7279\u5f81\u503c\u8ba1\u7b97\u5f97\u5230");
    model.component("comp2").variable("var2").set("frequency", "emw2.freq", "\u9891\u7387\uff0c\u6a21\u62df\u503c");

    model.component("comp2").mesh("mesh2").create("ftet1", "FreeTet");
    model.component("comp2").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "h_max_c/d_f");
    model.component("comp2").mesh("mesh2").feature("size").set("hgrad", 2);
    model.component("comp2").mesh("mesh2").feature("size").set("hcurve", 1);
    model.component("comp2").mesh("mesh2").feature("size").set("hnarrow", 0.1);
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").feature().copy("param", "std1/param");
    model.study("std2").feature().copy("eig", "std1/eig");
    model.study("std2").feature("eig").setSolveFor("/physics/emw", false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std2");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");
    model.batch("p2").run("compute");

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u573a (emw2)");
    model.result("pg3").set("data", "dset6");
    model.result("pg3").setIndex("looplevel", 1, 0);
    model.result("pg3").setIndex("looplevel", 4, 1);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("mslc1", "Multislice");
    model.result("pg3").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg3").feature("mslc1").set("smooth", "internal");
    model.result("pg3").feature("mslc1").set("data", "parent");
    model.result("pg3").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg3").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").label("\u7279\u5f81\u9891\u7387 (emw2)");
    model.result().numerical("gev2").set("data", "dset6");
    model.result().numerical("gev2").set("expr", new String[]{"emw2.freq", "emw2.Qfactor"});
    model.result().numerical("gev2").set("unit", new String[]{"GHz", "1"});
    model.result().table().create("tbl2", "Table");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").run();
    model.result().numerical("gev2").setResult();
    model.result("pg3").run();
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("expr", new String[]{"emw2.Ex", "emw2.Ey", "emw2.Ez"});
    model.result("pg3").feature("arwv1").set("descr", "\u7535\u573a");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("arwv2", "ArrowVolume");
    model.result("pg3").feature("arwv2").set("expr", new String[]{"emw2.Hx", "emw2.Hy", "emw2.Hz"});
    model.result("pg3").feature("arwv2").set("descr", "\u78c1\u573a");
    model.result("pg3").feature("arwv2").set("znumber", 1);
    model.result("pg3").feature("arwv2").set("color", "white");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset6");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "emw2.Qsh");
    model.result("pg4").feature("surf1").set("descr", "\u8868\u9762\u635f\u8017");
    model.result("pg4").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg4").run();
    model.result("pg4").label("\u8868\u9762\u635f\u8017 (emw2)");
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1").set("expr", new String[]{"emw2.Jsx", "emw2.Jsy", "emw2.Jsz"});
    model.result("pg4").feature("arws1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg4").feature("arws1").set("color", "blue");
    model.result("pg4").run();

    model.component().create("comp3", true);

    model.component("comp3").geom().create("geom3", 3);
    model.component("comp3").geom("geom3").geomRep("comsol");

    model.component("comp3").mesh().create("mesh3");
    model.component("comp3").mesh("mesh3").contribute("geom/detail", true);

    model.component("comp3").physics().create("emw3", "ElectromagneticWaves", "geom3");

    model.study("std1").feature("eig").setSolveFor("/physics/emw3", true);
    model.study("std2").feature("eig").setSolveFor("/physics/emw3", true);

    model.component("comp3").geom("geom3").run();

    model.study().create("std3");

    model.component("comp3").geom("geom3").create("sph1", "Sphere");
    model.component("comp3").geom("geom3").feature("sph1").set("r", "a_s");
    model.component("comp3").geom("geom3").runPre("fin");
    model.component("comp3").geom("geom3").run();

    model.component("comp3").physics("emw3").create("imp1", "Impedance", 2);
    model.component("comp3").physics("emw3").feature("imp1").selection().all();

    model.component("comp3").material().create("matlnk5", "Link");
    model.component("comp3").material().create("matlnk6", "Link");
    model.component("comp3").material("matlnk6").selection().geom("geom3", 2);
    model.component("comp3").material("matlnk6").selection().all();
    model.component("comp3").material("matlnk6").set("link", "mat2");

    model.component("comp3").cpl().create("intop5", "Integration");
    model.component("comp3").cpl("intop5").set("axisym", true);
    model.component("comp3").cpl("intop5").set("opname", "int_v");
    model.component("comp3").cpl("intop5").selection().all();
    model.component("comp3").cpl().create("intop6", "Integration");
    model.component("comp3").cpl("intop6").set("axisym", true);
    model.component("comp3").cpl("intop6").set("opname", "int_s");
    model.component("comp3").cpl("intop6").selection().geom("geom3", 2);
    model.component("comp3").cpl("intop6").selection().all();

    model.component("comp3").variable().create("var3");

//    To import content from file, use:
//    model.component("comp3").variable("var3").loadFile("FILENAME");
    model.component("comp3").variable("var3")
         .set("W_t", "int_v(emw3.Wav)", "\u5b58\u50a8\u7684\u5e73\u5747\u80fd\u91cf");
    model.component("comp3").variable("var3").set("P_d", "int_s(emw3.Qsh)", "\u8017\u6563\u529f\u7387");
    model.component("comp3").variable("var3")
         .set("Q_definition", "2*pi*emw3.freq*W_t/P_d", "Q \u56e0\u5b50\uff0c\u5b9a\u4e49");
    model.component("comp3").variable("var3")
         .set("Q_computed", "emw3.Qfactor", "Q \u56e0\u5b50\uff0c\u57fa\u4e8e\u7279\u5f81\u503c\u8ba1\u7b97\u5f97\u5230");
    model.component("comp3").variable("var3").set("frequency", "emw3.freq", "\u9891\u7387\uff0c\u6a21\u62df\u503c");

    model.component("comp3").mesh("mesh3").create("ftet1", "FreeTet");
    model.component("comp3").mesh("mesh3").feature("size").set("custom", true);
    model.component("comp3").mesh("mesh3").feature("size").set("hmax", "h_max_s/d_f");
    model.component("comp3").mesh("mesh3").feature("size").set("hgrad", 2);
    model.component("comp3").mesh("mesh3").feature("size").set("hcurve", 1);
    model.component("comp3").mesh("mesh3").feature("size").set("hnarrow", 0.1);
    model.component("comp3").mesh("mesh3").run();

    model.study("std3").feature().copy("param", "std2/param");
    model.study("std3").feature().copy("eig", "std2/eig");
    model.study("std3").feature("eig").setSolveFor("/physics/emw2", false);
    model.study("std3").showAutoSequences("all");
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol14");
    model.sol("sol14").study("std3");
    model.sol("sol14").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol14");
    model.batch("p3").run("compute");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (emw3)");
    model.result("pg5").set("data", "dset12");
    model.result("pg5").setIndex("looplevel", 1, 0);
    model.result("pg5").setIndex("looplevel", 4, 1);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").label("\u591a\u5207\u9762");
    model.result("pg5").feature("mslc1").set("smooth", "internal");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature("mslc1").feature().create("filt1", "Filter");
    model.result("pg5").feature("mslc1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").label("\u7279\u5f81\u9891\u7387 (emw3)");
    model.result().numerical("gev3").set("data", "dset12");
    model.result().numerical("gev3").set("expr", new String[]{"emw3.freq", "emw3.Qfactor"});

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical("gev3").set("unit", new String[]{"GHz", "1"});
    model.result().table().create("tbl3", "Table");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").run();
    model.result().numerical("gev3").setResult();
    model.result("pg5").run();
    model.result("pg5").create("arwv1", "ArrowVolume");
    model.result("pg5").feature("arwv1").set("expr", new String[]{"emw3.Ex", "emw3.Ey", "emw3.Ez"});
    model.result("pg5").feature("arwv1").set("descr", "\u7535\u573a");
    model.result("pg5").run();
    model.result("pg5").create("arwv2", "ArrowVolume");
    model.result("pg5").feature("arwv2").set("expr", new String[]{"emw3.Hx", "emw3.Hy", "emw3.Hz"});
    model.result("pg5").feature("arwv2").set("descr", "\u78c1\u573a");
    model.result("pg5").feature("arwv2").set("znumber", 1);
    model.result("pg5").feature("arwv2").set("color", "white");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset12");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", "emw3.Qsh");
    model.result("pg6").feature("surf1").set("descr", "\u8868\u9762\u635f\u8017");
    model.result("pg6").feature("surf1").set("colortable", "ThermalDark");
    model.result("pg6").run();
    model.result("pg6").label("\u8868\u9762\u635f\u8017 (emw3)");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("expr", new String[]{"emw3.Jsx", "emw3.Jsy", "emw3.Jsz"});
    model.result("pg6").feature("arws1").set("descr", "\u8868\u9762\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg6").feature("arws1").set("color", "blue");
    model.result("pg6").run();
    model.result().numerical().create("gev4", "EvalGlobal");
    model.result().numerical("gev4").set("data", "dset2");
    model.result().numerical("gev4").setIndex("looplevelinput", "first", 0);
    model.result().numerical("gev4").set("tablecols", "inner");
    model.result().numerical("gev4").set("expr", new String[]{"Q_computed"});
    model.result().numerical("gev4")
         .set("descr", new String[]{"Q \u56e0\u5b50\uff0c\u57fa\u4e8e\u7279\u5f81\u503c\u8ba1\u7b97\u5f97\u5230"});
    model.result().numerical("gev4").set("unit", new String[]{"1"});
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u5168\u5c40\u8ba1\u7b97 4");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").setResult();
    model.result().numerical("gev4").set("data", "dset6");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").appendResult();
    model.result().numerical("gev4").set("data", "dset12");
    model.result().numerical("gev4").set("table", "tbl4");
    model.result().numerical("gev4").appendResult();
    model.result().numerical().duplicate("gev5", "gev4");
    model.result().numerical("gev5").set("data", "dset2");
    model.result().numerical("gev5").set("expr", new String[]{"Q_definition"});
    model.result().numerical("gev5").set("descr", new String[]{"Q \u56e0\u5b50\uff0c\u5b9a\u4e49"});
    model.result().numerical("gev5").set("unit", new String[]{"1"});
    model.result().table().create("tbl5", "Table");
    model.result().table("tbl5").comments("\u5168\u5c40\u8ba1\u7b97 5");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").setResult();
    model.result().numerical("gev5").set("data", "dset6");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").appendResult();
    model.result().numerical("gev5").set("data", "dset12");
    model.result().numerical("gev5").set("table", "tbl5");
    model.result().numerical("gev5").appendResult();
    model.result().numerical().duplicate("gev6", "gev4");
    model.result().numerical("gev6").set("data", "dset2");
    model.result().numerical("gev6").set("expr", new String[]{"frequency"});
    model.result().numerical("gev6").set("descr", new String[]{"\u9891\u7387\uff0c\u6a21\u62df\u503c"});
    model.result().numerical("gev6").set("unit", new String[]{"Hz"});
    model.result().table().create("tbl6", "Table");
    model.result().table("tbl6").comments("\u5168\u5c40\u8ba1\u7b97 6");
    model.result().numerical("gev6").set("table", "tbl6");
    model.result().numerical("gev6").setResult();
    model.result().numerical("gev6").set("data", "dset6");
    model.result().numerical("gev6").set("table", "tbl6");
    model.result().numerical("gev6").appendResult();
    model.result().numerical("gev6").set("data", "dset12");
    model.result().numerical("gev6").set("table", "tbl6");
    model.result().numerical("gev6").appendResult();

    model.study("std1").feature("eig").setSolveFor("/physics/emw2", false);
    model.study("std1").feature("eig").setSolveFor("/physics/emw3", false);
    model.study("std2").feature("eig").setSolveFor("/physics/emw3", false);

    model.title("\u7a7a\u8154\u8c10\u632f\u5668\u7684 Q \u56e0\u5b50\u548c\u8c10\u632f\u9891\u7387\u8ba1\u7b97");

    model
         .description("\u8fd9\u4e9b\u6a21\u578b\u7528\u4e8e\u8ba1\u7b97\u77e9\u5f62\u3001\u5706\u67f1\u5f62\u548c\u7403\u5f62\u8154\u7684\u8c10\u632f\u9891\u7387\u548c Q \u56e0\u5b50\u3002\u8fd9\u4e9b\u51e0\u4f55\u5177\u6709\u5df2\u77e5\u7684\u89e3\u6790\u89e3\u3002\u672c\u4f8b\u5c55\u793a\u4e86\u591a\u4e2a\u6a21\u578b\u548c\u6bd4\u8f83\u7ed3\u679c\uff0c\u5e76\u663e\u793a\u4e86\u7f51\u683c\u7ec6\u5316\u7814\u7a76\u3002");

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

    model.label("cavity_resonators.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}

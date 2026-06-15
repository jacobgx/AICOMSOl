/*
 * hexagonal_plasmonic_color_filter.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:38 by COMSOL 6.3.0.290. */
public class hexagonal_plasmonic_color_filter {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Gratings_and_Metamaterials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("wave", "Wavelength");
    model.study("std1").feature("wave").set("ftplistmethod", "manual");
    model.study("std1").feature("wave").set("solnum", "auto");
    model.study("std1").feature("wave").set("notsolnum", "auto");
    model.study("std1").feature("wave").set("outputmap", new String[]{});
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").set("ngenAUX", "1");
    model.study("std1").feature("wave").set("goalngenAUX", "1");
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("a", "420[nm]", "\u516d\u8fb9\u5f62\u57fa\u672c\u5355\u5143\u5468\u671f");
    model.param().set("d_hex", "a/sqrt(3)", "\u516d\u8fb9\u5f62\u57fa\u672c\u5355\u5143\u8fb9\u957f");
    model.param().set("d_rect_x", "a*sqrt(3)", "\u77e9\u5f62\u57fa\u672c\u5355\u5143\u5bbd\u5ea6");
    model.param().set("d_rect_y", "a", "\u77e9\u5f62\u57fa\u672c\u5355\u5143\u6df1\u5ea6");
    model.param().set("height", "1200[nm]", "\u57df\u9ad8\u5ea6");
    model.param().set("t_Al", "150[nm]", "\u94dd\u5c42\u539a\u5ea6");
    model.param().set("r0", "100[nm]", "\u5b54\u534a\u5f84");
    model.param().set("t_SOG", "160[nm]", "\u65cb\u6d82\u73bb\u7483\u539a\u5ea6");
    model.param().set("n_SOG", "1.39", "\u6298\u5c04\u7387\uff0c\u65cb\u6d82\u73bb\u7483");
    model.param().set("n_SiO2_max", "1.55", "\u6700\u5927\u6298\u5c04\u7387\uff0c\u4e8c\u6c27\u5316\u7845");
    model.param().set("lda_min", "555[nm]", "\u6700\u5c0f\u6ce2\u957f");
    model.param().set("lda_max", "595[nm]", "\u6700\u5927\u6ce2\u957f");
    model.param().set("lda_step", "2[nm]", "\u6ce2\u957f\u6b65\u957f");

    model.component("comp1").geom("geom1").lengthUnit("nm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "d_hex", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "d_hex/2", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "a/2", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-d_hex/2", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "a/2", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-d_hex", 3, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-d_hex/2", 4, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-a/2", 4, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "d_hex/2", 5, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").setIndex("table", "-a/2", 5, 1);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "height/2-t_Al/2", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "height/2+t_Al/2", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "height/2+t_Al/2+t_SOG", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "height", 3);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r0");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "t_Al");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "height/2-t_Al/2"});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

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
    model.material("mat2").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.material("mat2").propertyGroup("RefractiveIndex").func().create("int1", "Interpolation");
    model.material("mat2").propertyGroup("RefractiveIndex").func().create("int2", "Interpolation");
    model.material("mat2").label("Al (Aluminium) (Rakic et al. 1998: Brendel-Bormann model; n,k 0.0620-248 um)");
    model.material("mat2").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"6.1992e-02", "6.6415e-01"}, 
         {"6.4630e-02", "6.2647e-01"}, 
         {"6.7381e-02", "5.8276e-01"}, 
         {"7.0249e-02", "5.3121e-01"}, 
         {"7.3239e-02", "4.6884e-01"}, 
         {"7.6355e-02", "3.9013e-01"}, 
         {"7.9605e-02", "2.8200e-01"}, 
         {"8.2993e-02", "1.1588e-01"}, 
         {"8.6525e-02", "5.5295e-02"}, 
         {"9.0208e-02", "4.4408e-02"}, 
         {"9.4047e-02", "4.0584e-02"}, 
         {"9.8049e-02", "3.9232e-02"}, 
         {"1.0222e-01", "3.9141e-02"}, 
         {"1.0657e-01", "3.9857e-02"}, 
         {"1.1111e-01", "4.1181e-02"}, 
         {"1.1584e-01", "4.3024e-02"}, 
         {"1.2077e-01", "4.5350e-02"}, 
         {"1.2591e-01", "4.8158e-02"}, 
         {"1.3127e-01", "5.1461e-02"}, 
         {"1.3685e-01", "5.5281e-02"}, 
         {"1.4268e-01", "5.9640e-02"}, 
         {"1.4875e-01", "6.4557e-02"}, 
         {"1.5508e-01", "7.0040e-02"}, 
         {"1.6168e-01", "7.6091e-02"}, 
         {"1.6856e-01", "8.2703e-02"}, 
         {"1.7573e-01", "8.9866e-02"}, 
         {"1.8321e-01", "9.7571e-02"}, 
         {"1.9101e-01", "1.0582e-01"}, 
         {"1.9914e-01", "1.1461e-01"}, 
         {"2.0762e-01", "1.2398e-01"}, 
         {"2.1645e-01", "1.3398e-01"}, 
         {"2.2566e-01", "1.4468e-01"}, 
         {"2.3527e-01", "1.5617e-01"}, 
         {"2.4528e-01", "1.6860e-01"}, 
         {"2.5572e-01", "1.8210e-01"}, 
         {"2.6660e-01", "1.9687e-01"}, 
         {"2.7795e-01", "2.1312e-01"}, 
         {"2.8978e-01", "2.3109e-01"}, 
         {"3.0211e-01", "2.5107e-01"}, 
         {"3.1497e-01", "2.7336e-01"}, 
         {"3.2837e-01", "2.9832e-01"}, 
         {"3.4235e-01", "3.2634e-01"}, 
         {"3.5692e-01", "3.5785e-01"}, 
         {"3.7211e-01", "3.9330e-01"}, 
         {"3.8795e-01", "4.3317e-01"}, 
         {"4.0446e-01", "4.7790e-01"}, 
         {"4.2167e-01", "5.2788e-01"}, 
         {"4.3962e-01", "5.8336e-01"}, 
         {"4.5833e-01", "6.4439e-01"}, 
         {"4.7783e-01", "7.1074e-01"}, 
         {"4.9817e-01", "7.8186e-01"}, 
         {"5.1937e-01", "8.5697e-01"}, 
         {"5.4147e-01", "9.3525e-01"}, 
         {"5.6452e-01", "1.0162e+00"}, 
         {"5.8855e-01", "1.1005e+00"}, 
         {"6.1359e-01", "1.1911e+00"}, 
         {"6.3971e-01", "1.2952e+00"}, 
         {"6.6693e-01", "1.4286e+00"}, 
         {"6.9532e-01", "1.6203e+00"}, 
         {"7.2491e-01", "1.9115e+00"}, 
         {"7.5576e-01", "2.3052e+00"}, 
         {"7.8793e-01", "2.6523e+00"}, 
         {"8.2146e-01", "2.7061e+00"}, 
         {"8.5642e-01", "2.4465e+00"}, 
         {"8.9287e-01", "2.0948e+00"}, 
         {"9.3087e-01", "1.8089e+00"}, 
         {"9.7049e-01", "1.6120e+00"}, 
         {"1.0118e+00", "1.4821e+00"}, 
         {"1.0549e+00", "1.3974e+00"}, 
         {"1.0997e+00", "1.3440e+00"}, 
         {"1.1465e+00", "1.3136e+00"}, 
         {"1.1953e+00", "1.3012e+00"}, 
         {"1.2462e+00", "1.3039e+00"}, 
         {"1.2993e+00", "1.3197e+00"}, 
         {"1.3546e+00", "1.3476e+00"}, 
         {"1.4122e+00", "1.3868e+00"}, 
         {"1.4723e+00", "1.4370e+00"}, 
         {"1.5350e+00", "1.4979e+00"}, 
         {"1.6003e+00", "1.5694e+00"}, 
         {"1.6684e+00", "1.6518e+00"}, 
         {"1.7394e+00", "1.7451e+00"}, 
         {"1.8134e+00", "1.8497e+00"}, 
         {"1.8906e+00", "1.9659e+00"}, 
         {"1.9711e+00", "2.0942e+00"}, 
         {"2.0550e+00", "2.2349e+00"}, 
         {"2.1424e+00", "2.3887e+00"}, 
         {"2.2336e+00", "2.5562e+00"}, 
         {"2.3287e+00", "2.7379e+00"}, 
         {"2.4278e+00", "2.9346e+00"}, 
         {"2.5311e+00", "3.1468e+00"}, 
         {"2.6388e+00", "3.3754e+00"}, 
         {"2.7511e+00", "3.6209e+00"}, 
         {"2.8682e+00", "3.8841e+00"}, 
         {"2.9903e+00", "4.1657e+00"}, 
         {"3.1175e+00", "4.4664e+00"}, 
         {"3.2502e+00", "4.7867e+00"}, 
         {"3.3885e+00", "5.1273e+00"}, 
         {"3.5328e+00", "5.4887e+00"}, 
         {"3.6831e+00", "5.8714e+00"}, 
         {"3.8399e+00", "6.2757e+00"}, 
         {"4.0033e+00", "6.7019e+00"}, 
         {"4.1737e+00", "7.1501e+00"}, 
         {"4.3513e+00", "7.6206e+00"}, 
         {"4.5365e+00", "8.1133e+00"}, 
         {"4.7296e+00", "8.6281e+00"}, 
         {"4.9308e+00", "9.1649e+00"}, 
         {"5.1407e+00", "9.7236e+00"}, 
         {"5.3595e+00", "1.0304e+01"}, 
         {"5.5876e+00", "1.0906e+01"}, 
         {"5.8254e+00", "1.1530e+01"}, 
         {"6.0733e+00", "1.2175e+01"}, 
         {"6.3318e+00", "1.2843e+01"}, 
         {"6.6013e+00", "1.3534e+01"}, 
         {"6.8822e+00", "1.4250e+01"}, 
         {"7.1751e+00", "1.4991e+01"}, 
         {"7.4805e+00", "1.5760e+01"}, 
         {"7.7988e+00", "1.6560e+01"}, 
         {"8.1307e+00", "1.7394e+01"}, 
         {"8.4768e+00", "1.8266e+01"}, 
         {"8.8376e+00", "1.9179e+01"}, 
         {"9.2137e+00", "2.0140e+01"}, 
         {"9.6058e+00", "2.1153e+01"}, 
         {"1.0015e+01", "2.2224e+01"}, 
         {"1.0441e+01", "2.3359e+01"}, 
         {"1.0885e+01", "2.4565e+01"}, 
         {"1.1348e+01", "2.5849e+01"}, 
         {"1.1831e+01", "2.7217e+01"}, 
         {"1.2335e+01", "2.8677e+01"}, 
         {"1.2860e+01", "3.0235e+01"}, 
         {"1.3407e+01", "3.1898e+01"}, 
         {"1.3978e+01", "3.3672e+01"}, 
         {"1.4573e+01", "3.5565e+01"}, 
         {"1.5193e+01", "3.7582e+01"}, 
         {"1.5840e+01", "3.9728e+01"}, 
         {"1.6514e+01", "4.2010e+01"}, 
         {"1.7216e+01", "4.4432e+01"}, 
         {"1.7949e+01", "4.6998e+01"}, 
         {"1.8713e+01", "4.9712e+01"}, 
         {"1.9510e+01", "5.2578e+01"}, 
         {"2.0340e+01", "5.5597e+01"}, 
         {"2.1206e+01", "5.8773e+01"}, 
         {"2.2108e+01", "6.2105e+01"}, 
         {"2.3049e+01", "6.5596e+01"}, 
         {"2.4030e+01", "6.9244e+01"}, 
         {"2.5053e+01", "7.3050e+01"}, 
         {"2.6119e+01", "7.7013e+01"}, 
         {"2.7230e+01", "8.1130e+01"}, 
         {"2.8389e+01", "8.5400e+01"}, 
         {"2.9598e+01", "8.9820e+01"}, 
         {"3.0857e+01", "9.4387e+01"}, 
         {"3.2170e+01", "9.9098e+01"}, 
         {"3.3540e+01", "1.0395e+02"}, 
         {"3.4967e+01", "1.0894e+02"}, 
         {"3.6455e+01", "1.1406e+02"}, 
         {"3.8007e+01", "1.1931e+02"}, 
         {"3.9624e+01", "1.2469e+02"}, 
         {"4.1311e+01", "1.3018e+02"}, 
         {"4.3069e+01", "1.3580e+02"}, 
         {"4.4902e+01", "1.4153e+02"}, 
         {"4.6813e+01", "1.4737e+02"}, 
         {"4.8805e+01", "1.5332e+02"}, 
         {"5.0882e+01", "1.5938e+02"}, 
         {"5.3048e+01", "1.6554e+02"}, 
         {"5.5305e+01", "1.7180e+02"}, 
         {"5.7659e+01", "1.7817e+02"}, 
         {"6.0113e+01", "1.8463e+02"}, 
         {"6.2671e+01", "1.9119e+02"}, 
         {"6.5339e+01", "1.9785e+02"}, 
         {"6.8119e+01", "2.0461e+02"}, 
         {"7.1019e+01", "2.1147e+02"}, 
         {"7.4041e+01", "2.1842e+02"}, 
         {"7.7192e+01", "2.2547e+02"}, 
         {"8.0478e+01", "2.3263e+02"}, 
         {"8.3903e+01", "2.3988e+02"}, 
         {"8.7473e+01", "2.4724e+02"}, 
         {"9.1196e+01", "2.5471e+02"}, 
         {"9.5078e+01", "2.6228e+02"}, 
         {"9.9124e+01", "2.6996e+02"}, 
         {"1.0334e+02", "2.7776e+02"}, 
         {"1.0774e+02", "2.8567e+02"}, 
         {"1.1233e+02", "2.9370e+02"}, 
         {"1.1711e+02", "3.0185e+02"}, 
         {"1.2209e+02", "3.1012e+02"}, 
         {"1.2729e+02", "3.1852e+02"}, 
         {"1.3270e+02", "3.2705e+02"}, 
         {"1.3835e+02", "3.3572e+02"}, 
         {"1.4424e+02", "3.4453e+02"}, 
         {"1.5038e+02", "3.5348e+02"}, 
         {"1.5678e+02", "3.6257e+02"}, 
         {"1.6345e+02", "3.7182e+02"}, 
         {"1.7041e+02", "3.8122e+02"}, 
         {"1.7766e+02", "3.9078e+02"}, 
         {"1.8522e+02", "4.0050e+02"}, 
         {"1.9310e+02", "4.1039e+02"}, 
         {"2.0132e+02", "4.2045e+02"}, 
         {"2.0989e+02", "4.3068e+02"}, 
         {"2.1882e+02", "4.4110e+02"}, 
         {"2.2814e+02", "4.5171e+02"}, 
         {"2.3785e+02", "4.6250e+02"}, 
         {"2.4797e+02", "4.7349e+02"}});
    model.material("mat2").propertyGroup("RefractiveIndex").func("int1").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("RefractiveIndex").func("int1").set("argunit", new String[]{"um"});
    model.material("mat2").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"6.1992e-02", "9.2039e-03"}, 
         {"6.4630e-02", "1.1074e-02"}, 
         {"6.7381e-02", "1.3512e-02"}, 
         {"7.0249e-02", "1.6828e-02"}, 
         {"7.3239e-02", "2.1649e-02"}, 
         {"7.6355e-02", "2.9549e-02"}, 
         {"7.9605e-02", "4.6438e-02"}, 
         {"8.2993e-02", "1.2841e-01"}, 
         {"8.6525e-02", "3.0590e-01"}, 
         {"9.0208e-02", "4.3313e-01"}, 
         {"9.4047e-02", "5.3919e-01"}, 
         {"9.8049e-02", "6.3492e-01"}, 
         {"1.0222e-01", "7.2492e-01"}, 
         {"1.0657e-01", "8.1159e-01"}, 
         {"1.1111e-01", "8.9644e-01"}, 
         {"1.1584e-01", "9.8045e-01"}, 
         {"1.2077e-01", "1.0643e+00"}, 
         {"1.2591e-01", "1.1487e+00"}, 
         {"1.3127e-01", "1.2338e+00"}, 
         {"1.3685e-01", "1.3202e+00"}, 
         {"1.4268e-01", "1.4080e+00"}, 
         {"1.4875e-01", "1.4975e+00"}, 
         {"1.5508e-01", "1.5890e+00"}, 
         {"1.6168e-01", "1.6826e+00"}, 
         {"1.6856e-01", "1.7786e+00"}, 
         {"1.7573e-01", "1.8771e+00"}, 
         {"1.8321e-01", "1.9783e+00"}, 
         {"1.9101e-01", "2.0826e+00"}, 
         {"1.9914e-01", "2.1901e+00"}, 
         {"2.0762e-01", "2.3011e+00"}, 
         {"2.1645e-01", "2.4159e+00"}, 
         {"2.2566e-01", "2.5347e+00"}, 
         {"2.3527e-01", "2.6578e+00"}, 
         {"2.4528e-01", "2.7856e+00"}, 
         {"2.5572e-01", "2.9182e+00"}, 
         {"2.6660e-01", "3.0560e+00"}, 
         {"2.7795e-01", "3.1992e+00"}, 
         {"2.8978e-01", "3.3482e+00"}, 
         {"3.0211e-01", "3.5030e+00"}, 
         {"3.1497e-01", "3.6640e+00"}, 
         {"3.2837e-01", "3.8313e+00"}, 
         {"3.4235e-01", "4.0052e+00"}, 
         {"3.5692e-01", "4.1856e+00"}, 
         {"3.7211e-01", "4.3726e+00"}, 
         {"3.8795e-01", "4.5661e+00"}, 
         {"4.0446e-01", "4.7659e+00"}, 
         {"4.2167e-01", "4.9717e+00"}, 
         {"4.3962e-01", "5.1830e+00"}, 
         {"4.5833e-01", "5.3994e+00"}, 
         {"4.7783e-01", "5.6203e+00"}, 
         {"4.9817e-01", "5.8455e+00"}, 
         {"5.1937e-01", "6.0750e+00"}, 
         {"5.4147e-01", "6.3099e+00"}, 
         {"5.6452e-01", "6.5519e+00"}, 
         {"5.8855e-01", "6.8042e+00"}, 
         {"6.1359e-01", "7.0711e+00"}, 
         {"6.3971e-01", "7.3576e+00"}, 
         {"6.6693e-01", "7.6672e+00"}, 
         {"6.9532e-01", "7.9931e+00"}, 
         {"7.2491e-01", "8.2933e+00"}, 
         {"7.5576e-01", "8.4555e+00"}, 
         {"7.8793e-01", "8.3611e+00"}, 
         {"8.2146e-01", "8.1106e+00"}, 
         {"8.5642e-01", "7.9982e+00"}, 
         {"8.9287e-01", "8.1553e+00"}, 
         {"9.3087e-01", "8.4990e+00"}, 
         {"9.7049e-01", "8.9296e+00"}, 
         {"1.0118e+00", "9.3982e+00"}, 
         {"1.0549e+00", "9.8877e+00"}, 
         {"1.0997e+00", "1.0393e+01"}, 
         {"1.1465e+00", "1.0915e+01"}, 
         {"1.1953e+00", "1.1452e+01"}, 
         {"1.2462e+00", "1.2007e+01"}, 
         {"1.2993e+00", "1.2581e+01"}, 
         {"1.3546e+00", "1.3173e+01"}, 
         {"1.4122e+00", "1.3786e+01"}, 
         {"1.4723e+00", "1.4421e+01"}, 
         {"1.5350e+00", "1.5077e+01"}, 
         {"1.6003e+00", "1.5757e+01"}, 
         {"1.6684e+00", "1.6460e+01"}, 
         {"1.7394e+00", "1.7188e+01"}, 
         {"1.8134e+00", "1.7942e+01"}, 
         {"1.8906e+00", "1.8723e+01"}, 
         {"1.9711e+00", "1.9531e+01"}, 
         {"2.0550e+00", "2.0367e+01"}, 
         {"2.1424e+00", "2.1233e+01"}, 
         {"2.2336e+00", "2.2128e+01"}, 
         {"2.3287e+00", "2.3054e+01"}, 
         {"2.4278e+00", "2.4012e+01"}, 
         {"2.5311e+00", "2.5002e+01"}, 
         {"2.6388e+00", "2.6025e+01"}, 
         {"2.7511e+00", "2.7082e+01"}, 
         {"2.8682e+00", "2.8173e+01"}, 
         {"2.9903e+00", "2.9300e+01"}, 
         {"3.1175e+00", "3.0463e+01"}, 
         {"3.2502e+00", "3.1662e+01"}, 
         {"3.3885e+00", "3.2898e+01"}, 
         {"3.5328e+00", "3.4172e+01"}, 
         {"3.6831e+00", "3.5484e+01"}, 
         {"3.8399e+00", "3.6836e+01"}, 
         {"4.0033e+00", "3.8228e+01"}, 
         {"4.1737e+00", "3.9660e+01"}, 
         {"4.3513e+00", "4.1135e+01"}, 
         {"4.5365e+00", "4.2652e+01"}, 
         {"4.7296e+00", "4.4214e+01"}, 
         {"4.9308e+00", "4.5821e+01"}, 
         {"5.1407e+00", "4.7476e+01"}, 
         {"5.3595e+00", "4.9180e+01"}, 
         {"5.5876e+00", "5.0936e+01"}, 
         {"5.8254e+00", "5.2747e+01"}, 
         {"6.0733e+00", "5.4615e+01"}, 
         {"6.3318e+00", "5.6545e+01"}, 
         {"6.6013e+00", "5.8539e+01"}, 
         {"6.8822e+00", "6.0601e+01"}, 
         {"7.1751e+00", "6.2736e+01"}, 
         {"7.4805e+00", "6.4948e+01"}, 
         {"7.7988e+00", "6.7242e+01"}, 
         {"8.1307e+00", "6.9620e+01"}, 
         {"8.4768e+00", "7.2089e+01"}, 
         {"8.8376e+00", "7.4651e+01"}, 
         {"9.2137e+00", "7.7311e+01"}, 
         {"9.6058e+00", "8.0072e+01"}, 
         {"1.0015e+01", "8.2936e+01"}, 
         {"1.0441e+01", "8.5906e+01"}, 
         {"1.0885e+01", "8.8985e+01"}, 
         {"1.1348e+01", "9.2173e+01"}, 
         {"1.1831e+01", "9.5470e+01"}, 
         {"1.2335e+01", "9.8878e+01"}, 
         {"1.2860e+01", "1.0239e+02"}, 
         {"1.3407e+01", "1.0602e+02"}, 
         {"1.3978e+01", "1.0975e+02"}, 
         {"1.4573e+01", "1.1359e+02"}, 
         {"1.5193e+01", "1.1752e+02"}, 
         {"1.5840e+01", "1.2156e+02"}, 
         {"1.6514e+01", "1.2568e+02"}, 
         {"1.7216e+01", "1.2990e+02"}, 
         {"1.7949e+01", "1.3420e+02"}, 
         {"1.8713e+01", "1.3858e+02"}, 
         {"1.9510e+01", "1.4304e+02"}, 
         {"2.0340e+01", "1.4757e+02"}, 
         {"2.1206e+01", "1.5216e+02"}, 
         {"2.2108e+01", "1.5681e+02"}, 
         {"2.3049e+01", "1.6151e+02"}, 
         {"2.4030e+01", "1.6626e+02"}, 
         {"2.5053e+01", "1.7106e+02"}, 
         {"2.6119e+01", "1.7589e+02"}, 
         {"2.7230e+01", "1.8076e+02"}, 
         {"2.8389e+01", "1.8565e+02"}, 
         {"2.9598e+01", "1.9058e+02"}, 
         {"3.0857e+01", "1.9553e+02"}, 
         {"3.2170e+01", "2.0050e+02"}, 
         {"3.3540e+01", "2.0549e+02"}, 
         {"3.4967e+01", "2.1050e+02"}, 
         {"3.6455e+01", "2.1553e+02"}, 
         {"3.8007e+01", "2.2057e+02"}, 
         {"3.9624e+01", "2.2563e+02"}, 
         {"4.1311e+01", "2.3071e+02"}, 
         {"4.3069e+01", "2.3582e+02"}, 
         {"4.4902e+01", "2.4094e+02"}, 
         {"4.6813e+01", "2.4609e+02"}, 
         {"4.8805e+01", "2.5127e+02"}, 
         {"5.0882e+01", "2.5648e+02"}, 
         {"5.3048e+01", "2.6173e+02"}, 
         {"5.5305e+01", "2.6701e+02"}, 
         {"5.7659e+01", "2.7234e+02"}, 
         {"6.0113e+01", "2.7772e+02"}, 
         {"6.2671e+01", "2.8315e+02"}, 
         {"6.5339e+01", "2.8863e+02"}, 
         {"6.8119e+01", "2.9418e+02"}, 
         {"7.1019e+01", "2.9980e+02"}, 
         {"7.4041e+01", "3.0549e+02"}, 
         {"7.7192e+01", "3.1126e+02"}, 
         {"8.0478e+01", "3.1711e+02"}, 
         {"8.3903e+01", "3.2305e+02"}, 
         {"8.7473e+01", "3.2908e+02"}, 
         {"9.1196e+01", "3.3521e+02"}, 
         {"9.5078e+01", "3.4145e+02"}, 
         {"9.9124e+01", "3.4779e+02"}, 
         {"1.0334e+02", "3.5425e+02"}, 
         {"1.0774e+02", "3.6082e+02"}, 
         {"1.1233e+02", "3.6752e+02"}, 
         {"1.1711e+02", "3.7434e+02"}, 
         {"1.2209e+02", "3.8130e+02"}, 
         {"1.2729e+02", "3.8840e+02"}, 
         {"1.3270e+02", "3.9564e+02"}, 
         {"1.3835e+02", "4.0303e+02"}, 
         {"1.4424e+02", "4.1057e+02"}, 
         {"1.5038e+02", "4.1826e+02"}, 
         {"1.5678e+02", "4.2612e+02"}, 
         {"1.6345e+02", "4.3415e+02"}, 
         {"1.7041e+02", "4.4234e+02"}, 
         {"1.7766e+02", "4.5071e+02"}, 
         {"1.8522e+02", "4.5926e+02"}, 
         {"1.9310e+02", "4.6800e+02"}, 
         {"2.0132e+02", "4.7693e+02"}, 
         {"2.0989e+02", "4.8605e+02"}, 
         {"2.1882e+02", "4.9537e+02"}, 
         {"2.2814e+02", "5.0490e+02"}, 
         {"2.3785e+02", "5.1463e+02"}, 
         {"2.4797e+02", "5.2458e+02"}});

    return model;
  }

  public static Model run2(Model model) {
    model.material("mat2").propertyGroup("RefractiveIndex").func("int2").set("fununit", new String[]{"1"});
    model.material("mat2").propertyGroup("RefractiveIndex").func("int2").set("argunit", new String[]{"um"});
    model.material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.material("mat2").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.material("mat2").propertyGroup("RefractiveIndex").addInput("frequency");
    model.material().create("mat3", "Common", "");
    model.material("mat3").propertyGroup()
         .create("DispersionModelSellmeierModified2", "DispersionModelSellmeierModified2", "Sellmeier \u4fee\u6b63\uff0c\u7c7b\u578b 2");
    model.material("mat3").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.material("mat3")
         .label("SiO2 (Silicon dioxide, Silica, Quartz) (Ghosh 1999: a-Quartz, n(o) 0.198-2.05 um)");
    model.material("mat3").propertyGroup("DispersionModelSellmeierModified2")
         .set("ODsmc", new String[]{"1.28604141", "1.07044083", "1.10202242", "1.00585997e-2", "100"});
    model.material("mat3").propertyGroup("DispersionModelSellmeierModified2").set("Trefsmc", "22[degC]");
    model.material("mat3").propertyGroup("DispersionModelSellmeierModified2").set("Prefsmc", "0");
    model.material("mat3").propertyGroup("DispersionModelSellmeierModified2").addInput("frequency");
    model.material("mat3").propertyGroup("RefractiveIndex").addInput("frequency");
    model.material().create("mat4", "Common", "");
    model.material("mat4").label("\u65cb\u6d82\u73bb\u7483");

    model.component("comp1").label("\u516d\u8fb9\u5f62\u5355\u5143");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").label("\u7a7a\u6c14");
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").label("Al");
    model.component("comp1").material("matlnk2").selection().set(2);
    model.component("comp1").material("matlnk2").set("link", "mat2");
    model.component("comp1").material().create("matlnk3", "Link");
    model.component("comp1").material("matlnk3").label("SiO2");
    model.component("comp1").material("matlnk3").selection().set(1, 5);
    model.component("comp1").material("matlnk3").set("link", "mat3");
    model.component("comp1").material().create("matlnk4", "Link");
    model.component("comp1").material("matlnk4").label("\u65cb\u6d82\u73bb\u7483");
    model.component("comp1").material("matlnk4").selection().set(3);
    model.component("comp1").material("matlnk4").set("link", "mat4");
    model.material("mat4").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.material("mat4").propertyGroup("RefractiveIndex").set("n", new String[]{"n_SOG"});

    model.component("comp1").physics("ewfd").create("ps1", "PeriodicStructure", 3);
    model.component("comp1").physics("ewfd").feature("ps1").set("n2_mat", "UserDefined");
    model.component("comp1").physics("ewfd").feature("ps1").setIndex("n2", "n_SiO2_max", 0);

    model.study("std1").feature("wave").set("punit", "nm");
    model.study("std1").feature("wave").set("plist", "range(lda_min,lda_step,lda_max)");

    model.component("comp1").physics("ewfd").feature("ps1")
         .set("DiffractionOrderSpecification", "FromCurrentParameters");
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc2")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("fpc3")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp1").physics("ewfd").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("smooth", "internal");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0_0", "ewfd.Rorder_0_0_orth", "ewfd.Rtotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]\uff0c\u6b63\u4ea4", "\u603b\u53cd\u5c04\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "lambda0");
    model.result("pg2").feature("glob1").set("xdataunit", "nm");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", ""});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"ewfd.Torder_0_0", "ewfd.Torder_n1_n1_ip", "ewfd.Torder_n1_n1_op", "ewfd.Torder_n1_0_ip", "ewfd.Torder_n1_0_op", "ewfd.Torder_0_n1_ip", "ewfd.Torder_0_n1_op", "ewfd.Torder_0_0_orth", "ewfd.Torder_0_p1_ip", "ewfd.Torder_0_p1_op", 
         "ewfd.Torder_p1_0_ip", "ewfd.Torder_p1_0_op", "ewfd.Torder_p1_p1_ip", "ewfd.Torder_p1_p1_op", "ewfd.Ttotal"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,-1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,-1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,0]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,0]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,-1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,-1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]\uff0c\u6b63\u4ea4", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,1]\uff0c\u9762\u5916", 
         "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,0]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,0]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,1]\uff0c\u9762\u5916", "\u603b\u900f\u5c04\u7387"});
    model.result("pg3").label("\u900f\u5c04\u7387 (ewfd)");
    model.result("pg3").feature("glob1").set("titletype", "none");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u900f\u5c04\u7387 (1)");
    model.result("pg3").feature("glob1").set("xdataexpr", "lambda0");
    model.result("pg3").feature("glob1").set("xdataunit", "nm");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").label("\u6781\u5316\u56fe (ewfd)");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg4").setIndex("looplevelinput", "manual", 0);
    model.result("pg4").setIndex("looplevel", "1", 0);
    model.result("pg4").create("plz1", "Polarization");
    model.result("pg4").feature("plz1").set("linestyle", "solid");
    model.result("pg4").feature("plz1").set("linewidth", 2);
    model.result("pg4").feature("plz1").set("display", "0");
    model.result("pg4").feature("plz1").create("col1", "Color");
    model.result("pg4").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg4").feature("plz1").set("legend", true);
    model.result("pg4").feature("plz1").set("legendmethod", "manual");
    model.result("pg4").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg4").create("plz2", "Polarization");
    model.result("pg4").feature("plz2").set("linestyle", "dashed");
    model.result("pg4").feature("plz2").set("linewidth", 2);
    model.result("pg4").feature("plz2").set("display", "4");
    model.result("pg4").feature("plz2").create("col1", "Color");
    model.result("pg4").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("plz2").set("legend", true);
    model.result("pg4").feature("plz2").set("legendmethod", "manual");
    model.result("pg4").feature("plz2").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg4").create("plz3", "Polarization");
    model.result("pg4").feature("plz3").set("linestyle", "dashed");
    model.result("pg4").feature("plz3").set("linewidth", 2);
    model.result("pg4").feature("plz3").set("display", "1");
    model.result("pg4").feature("plz3").create("col1", "Color");
    model.result("pg4").feature("plz3").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz3").feature("col1").set("colorlegend", false);
    model.result("pg4").create("plz4", "Polarization");
    model.result("pg4").feature("plz4").set("linestyle", "dashed");
    model.result("pg4").feature("plz4").set("linewidth", 2);
    model.result("pg4").feature("plz4").set("display", "2");
    model.result("pg4").feature("plz4").create("col1", "Color");
    model.result("pg4").feature("plz4").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz4").feature("col1").set("colorlegend", false);
    model.result("pg4").create("plz5", "Polarization");
    model.result("pg4").feature("plz5").set("linestyle", "dashed");
    model.result("pg4").feature("plz5").set("linewidth", 2);
    model.result("pg4").feature("plz5").set("display", "3");
    model.result("pg4").feature("plz5").create("col1", "Color");
    model.result("pg4").feature("plz5").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz5").feature("col1").set("colorlegend", false);
    model.result("pg4").create("plz6", "Polarization");
    model.result("pg4").feature("plz6").set("linestyle", "dashed");
    model.result("pg4").feature("plz6").set("linewidth", 2);
    model.result("pg4").feature("plz6").set("display", "5");
    model.result("pg4").feature("plz6").create("col1", "Color");
    model.result("pg4").feature("plz6").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz6").feature("col1").set("colorlegend", false);
    model.result("pg4").create("plz7", "Polarization");
    model.result("pg4").feature("plz7").set("linestyle", "dashed");
    model.result("pg4").feature("plz7").set("linewidth", 2);
    model.result("pg4").feature("plz7").set("display", "6");
    model.result("pg4").feature("plz7").create("col1", "Color");
    model.result("pg4").feature("plz7").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz7").feature("col1").set("colorlegend", false);
    model.result("pg4").create("plz8", "Polarization");
    model.result("pg4").feature("plz8").set("linestyle", "dashed");
    model.result("pg4").feature("plz8").set("linewidth", 2);
    model.result("pg4").feature("plz8").set("display", "7");
    model.result("pg4").feature("plz8").create("col1", "Color");
    model.result("pg4").feature("plz8").feature("col1").set("colortable", "Cyclic");
    model.result("pg4").feature("plz8").feature("col1").set("colorlegend", false);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 11, 0);
    model.result("pg1").run();
    model.result("pg1").feature("mslc1").set("expr", "ewfd.Ey");
    model.result("pg1").feature("mslc1").set("ynumber", "0");
    model.result("pg1").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "ewfd.Rorder_0_0", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6b21 [0,0]", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "ewfd.Torder_0_0", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [0,0]", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "ewfd.RTtotal", 2);
    model.result("pg5").feature("glob1").setIndex("expr", "ewfd.Atotal", 3);
    model.result("pg5").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().dataset().create("arr1", "Array3D");
    model.result().dataset("arr1").set("type", "linear");
    model.result().dataset("arr1").set("linearsize", 10);
    model.result().dataset("arr1").set("displmethod", "manual");
    model.result().dataset("arr1").set("displ", new String[]{"0", "a", "0"});
    model.result().dataset().create("arr2", "Array3D");
    model.result().dataset("arr2").set("data", "arr1");
    model.result().dataset("arr2").set("type", "linear");
    model.result().dataset("arr2").set("linearsize", 2);
    model.result().dataset("arr2").set("displmethod", "manual");
    model.result().dataset("arr2").set("displ", new String[]{"sqrt(3)*a/2", "0", "0"});
    model.result().dataset("arr2").setIndex("displ", "a/2", 1);
    model.result().dataset("arr2").set("checkoverlap", false);
    model.result().dataset().create("arr3", "Array3D");
    model.result().dataset("arr3").set("data", "arr2");
    model.result().dataset("arr3").set("type", "linear");
    model.result().dataset("arr3").set("linearsize", 5);
    model.result().dataset("arr3").set("displmethod", "manual");
    model.result().dataset("arr3").set("displ", new String[]{"sqrt(3)*a", "0", "0"});
    model.result().dataset("arr3").set("checkoverlap", false);
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u516d\u8fb9\u5f62\u9635\u5217\u7684\u573a\u56fe");
    model.result("pg6").set("data", "arr3");
    model.result("pg6").setIndex("looplevel", 11, 0);
    model.result("pg6").set("edges", false);
    model.result("pg6").create("mslc1", "Multislice");
    model.result("pg6").feature("mslc1").set("expr", "ewfd.Ey");
    model.result("pg6").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg6").feature("mslc1").set("xcoord", "range(0[nm],sqrt(3)*a/2,9*sqrt(3)*a/2)");
    model.result("pg6").feature("mslc1").set("ynumber", "0");
    model.result("pg6").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg6").feature("mslc1").set("zcoord", "height/2+t_Al/2");
    model.result("pg6").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg6").run();
    model.result("pg6").feature("mslc1").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("expr", "ewfd.Ey");
    model.result("pg6").feature("vol1").set("inheritplot", "mslc1");
    model.result("pg6").feature("vol1").create("tran1", "Transparency");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").create("vol2", "Volume");
    model.result("pg6").feature("vol2").set("coloring", "uniform");
    model.result("pg6").feature("vol2").set("color", "gray");
    model.result("pg6").feature("vol2").create("sel1", "Selection");
    model.result("pg6").feature("vol2").feature("sel1").selection().set(2);
    model.result("pg6").run();
    model.result("pg6").feature("vol2").set("resolution", "norefine");
    model.result("pg6").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").label("\u77e9\u5f62\u5355\u5143");

    model.component("comp2").geom("geom2").lengthUnit("nm");
    model.component("comp2").geom("geom2").create("blk1", "Block");
    model.component("comp2").geom("geom2").feature("blk1").set("size", new String[]{"d_rect_x", "d_rect_y", "1"});
    model.component("comp2").geom("geom2").feature("blk1").setIndex("size", "height", 2);
    model.component("comp2").geom("geom2").feature("blk1").set("base", "center");
    model.component("comp2").geom("geom2").run("blk1");

    model.component("comp2").view("view4").set("renderwireframe", true);

    model.component("comp2").geom("geom2").feature().duplicate("blk2", "blk1");
    model.component("comp2").geom("geom2").feature("blk2").setIndex("size", "t_Al", 2);
    model.component("comp2").geom("geom2").run("blk2");
    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "r0");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "t_Al");
    model.component("comp2").geom("geom2").feature("cyl1").set("pos", new String[]{"0", "0", "-t_Al/2"});
    model.component("comp2").geom("geom2").run("cyl1");
    model.component("comp2").geom("geom2").feature().duplicate("cyl2", "cyl1");
    model.component("comp2").geom("geom2").feature("cyl2").set("pos", new String[]{"-d_rect_x/2", "0", "-t_Al/2"});
    model.component("comp2").geom("geom2").feature("cyl2").setIndex("pos", "-d_rect_y/2", 1);
    model.component("comp2").geom("geom2").run("cyl2");
    model.component("comp2").geom("geom2").feature().duplicate("blk3", "blk2");
    model.component("comp2").geom("geom2").run("blk3");
    model.component("comp2").geom("geom2").create("int1", "Intersection");
    model.component("comp2").geom("geom2").feature("int1").selection("input").set("blk3", "cyl2");
    model.component("comp2").geom("geom2").run("int1");
    model.component("comp2").geom("geom2").create("mir1", "Mirror");
    model.component("comp2").geom("geom2").feature("mir1").selection("input").set("int1");
    model.component("comp2").geom("geom2").feature("mir1").set("keep", true);
    model.component("comp2").geom("geom2").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp2").geom("geom2").run("mir1");
    model.component("comp2").geom("geom2").create("mir2", "Mirror");
    model.component("comp2").geom("geom2").feature("mir2").selection("input").set("int1", "mir1");
    model.component("comp2").geom("geom2").feature("mir2").set("keep", true);
    model.component("comp2").geom("geom2").feature("mir2").set("axis", new int[]{0, 1, 0});
    model.component("comp2").geom("geom2").run("mir2");
    model.component("comp2").geom("geom2").feature().duplicate("blk4", "blk3");
    model.component("comp2").geom("geom2").feature("blk4").setIndex("size", "t_SOG", 2);
    model.component("comp2").geom("geom2").feature("blk4").set("pos", new String[]{"0", "0", "(t_Al+t_SOG)/2"});
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").physics().create("ewfd2", "ElectromagneticWavesFrequencyDomain", "geom2");

    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", true);

    model.component("comp2").geom("geom2").run();

    model.study("std1").feature("wave").setSolveFor("/physics/ewfd2", false);

    model.component("comp2").material().create("matlnk5", "Link");
    model.component("comp2").material("matlnk5").label("\u7a7a\u6c14");
    model.component("comp2").material().create("matlnk6", "Link");
    model.component("comp2").material("matlnk6").label("Al");
    model.component("comp2").material("matlnk6").selection().set(5);
    model.component("comp2").material("matlnk6").set("link", "mat2");
    model.component("comp2").material().create("matlnk7", "Link");
    model.component("comp2").material("matlnk7").label("SiO2");
    model.component("comp2").material("matlnk7").selection().set(1, 2, 6, 7, 8, 9);
    model.component("comp2").material("matlnk7").set("link", "mat3");
    model.component("comp2").material().create("matlnk8", "Link");
    model.component("comp2").material("matlnk8").label("\u65cb\u6d82\u73bb\u7483");
    model.component("comp2").material("matlnk8").selection().set(3);
    model.component("comp2").material("matlnk8").set("link", "mat4");

    model.component("comp2").physics("ewfd2").create("ps1", "PeriodicStructure", 3);
    model.component("comp2").physics("ewfd2").feature("ps1").set("n2_mat", "UserDefined");
    model.component("comp2").physics("ewfd2").feature("ps1").setIndex("n2", "n_SiO2_max", 0);

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd2", true);
    model.study("std2").feature("wave").set("punit", "nm");
    model.study("std2").feature("wave").set("plist", "range(lda_min,lda_step,lda_max)");

    model.component("comp2").physics("ewfd2").feature("ps1")
         .set("DiffractionOrderSpecification", "FromCurrentParameters");
    model.component("comp2").physics("ewfd2").feature("ps1").feature("fpc1")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp2").physics("ewfd2").feature("ps1").feature("fpc2")
         .set("splitPeriodicConditionSelections", true);
    model.component("comp2").physics("ewfd2").feature("ps1").feature("pport1").runCommand("addDiffractionOrders");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").label("\u7535\u573a (ewfd2)");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").setIndex("looplevel", 21, 0);
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("mslc1", "Multislice");
    model.result("pg7").feature("mslc1").set("smooth", "internal");
    model.result("pg7").feature("mslc1").set("data", "parent");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", ""});
    model.result("pg8").feature("glob1")
         .set("expr", new String[]{"ewfd2.Rorder_0_0", "ewfd2.Rorder_n1_0_ip", "ewfd2.Rorder_n1_0_op", "ewfd2.Rorder_0_0_orth", "ewfd2.Rorder_p1_0_ip", "ewfd2.Rorder_p1_0_op", "ewfd2.Rtotal"});
    model.result("pg8").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [-1,0]\uff0c\u9762\u5185", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [-1,0]\uff0c\u9762\u5916", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [0,0]\uff0c\u6b63\u4ea4", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [1,0]\uff0c\u9762\u5185", "\u53cd\u5c04\u7387\uff0c\u9636\u6570 [1,0]\uff0c\u9762\u5916", "\u603b\u53cd\u5c04\u7387"});
    model.result("pg8").label("\u53cd\u5c04\u7387 (ewfd2)");
    model.result("pg8").feature("glob1").set("titletype", "none");
    model.result("pg8").feature("glob1").set("xdata", "expr");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "\u53cd\u5c04\u7387 (1)");
    model.result("pg8").feature("glob1").set("xdataexpr", "lambda0");
    model.result("pg8").feature("glob1").set("xdataunit", "nm");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1")
         .set("unit", new String[]{"", "", "", "", "", "", "", "", "", "", 
         "", "", "", "", "", "", "", "", "", "", 
         ""});
    model.result("pg9").feature("glob1")
         .set("expr", new String[]{"ewfd2.Torder_0_0", "ewfd2.Torder_n2_0_ip", "ewfd2.Torder_n2_0_op", "ewfd2.Torder_n1_n1_ip", "ewfd2.Torder_n1_n1_op", "ewfd2.Torder_n1_0_ip", "ewfd2.Torder_n1_0_op", "ewfd2.Torder_n1_p1_ip", "ewfd2.Torder_n1_p1_op", "ewfd2.Torder_0_n1_ip", 
         "ewfd2.Torder_0_n1_op", "ewfd2.Torder_0_0_orth", "ewfd2.Torder_0_p1_ip", "ewfd2.Torder_0_p1_op", "ewfd2.Torder_p1_n1_ip", "ewfd2.Torder_p1_n1_op", "ewfd2.Torder_p1_0_ip", "ewfd2.Torder_p1_0_op", "ewfd2.Torder_p1_p1_ip", "ewfd2.Torder_p1_p1_op", 
         "ewfd2.Ttotal"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-2,0]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-2,0]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,-1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,-1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,0]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,0]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [-1,1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,-1]\uff0c\u9762\u5185", 
         "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,-1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,0]\uff0c\u6b63\u4ea4", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [0,1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,-1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,-1]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,0]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,0]\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,1]\uff0c\u9762\u5185", "\u900f\u5c04\u7387\uff0c\u9636\u6570 [1,1]\uff0c\u9762\u5916", 
         "\u603b\u900f\u5c04\u7387"});
    model.result("pg9").label("\u900f\u5c04\u7387 (ewfd2)");
    model.result("pg9").feature("glob1").set("titletype", "none");
    model.result("pg9").feature("glob1").set("xdata", "expr");
    model.result("pg9").set("ylabelactive", true);
    model.result("pg9").set("ylabel", "\u900f\u5c04\u7387 (1)");
    model.result("pg9").feature("glob1").set("xdataexpr", "lambda0");
    model.result("pg9").feature("glob1").set("xdataunit", "nm");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("xdatasolnumtype", "all");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").label("\u6781\u5316\u56fe (ewfd2)");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").set("titletype", "manual");
    model.result("pg10").set("title", "\u6781\u5316\u72b6\u6001\uff0cColor\uff1aPhase\uff08Radians\uff09");
    model.result("pg10").setIndex("looplevelinput", "manual", 0);
    model.result("pg10").setIndex("looplevel", "1", 0);
    model.result("pg10").create("plz1", "Polarization");
    model.result("pg10").feature("plz1").set("linestyle", "solid");
    model.result("pg10").feature("plz1").set("linewidth", 2);
    model.result("pg10").feature("plz1").set("display", "1");
    model.result("pg10").feature("plz1").create("col1", "Color");
    model.result("pg10").feature("plz1").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz1").feature("col1").set("colorlegend", true);
    model.result("pg10").feature("plz1").set("legend", true);
    model.result("pg10").feature("plz1").set("legendmethod", "manual");
    model.result("pg10").feature("plz1").setIndex("legends", "\u53cd\u5c04", 0);
    model.result("pg10").create("plz2", "Polarization");
    model.result("pg10").feature("plz2").set("linestyle", "solid");
    model.result("pg10").feature("plz2").set("linewidth", 2);
    model.result("pg10").feature("plz2").set("display", "0");
    model.result("pg10").feature("plz2").create("col1", "Color");
    model.result("pg10").feature("plz2").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz2").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz3", "Polarization");
    model.result("pg10").feature("plz3").set("linestyle", "solid");
    model.result("pg10").feature("plz3").set("linewidth", 2);
    model.result("pg10").feature("plz3").set("display", "2");
    model.result("pg10").feature("plz3").create("col1", "Color");
    model.result("pg10").feature("plz3").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz3").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz4", "Polarization");
    model.result("pg10").feature("plz4").set("linestyle", "dashed");
    model.result("pg10").feature("plz4").set("linewidth", 2);
    model.result("pg10").feature("plz4").set("display", "8");
    model.result("pg10").feature("plz4").create("col1", "Color");
    model.result("pg10").feature("plz4").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz4").feature("col1").set("colorlegend", false);
    model.result("pg10").feature("plz4").set("legend", true);
    model.result("pg10").feature("plz4").set("legendmethod", "manual");
    model.result("pg10").feature("plz4").setIndex("legends", "\u4f20\u8f93", 0);
    model.result("pg10").create("plz5", "Polarization");
    model.result("pg10").feature("plz5").set("linestyle", "dashed");
    model.result("pg10").feature("plz5").set("linewidth", 2);
    model.result("pg10").feature("plz5").set("display", "3");
    model.result("pg10").feature("plz5").create("col1", "Color");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg10").feature("plz5").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz5").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz6", "Polarization");
    model.result("pg10").feature("plz6").set("linestyle", "dashed");
    model.result("pg10").feature("plz6").set("linewidth", 2);
    model.result("pg10").feature("plz6").set("display", "4");
    model.result("pg10").feature("plz6").create("col1", "Color");
    model.result("pg10").feature("plz6").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz6").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz7", "Polarization");
    model.result("pg10").feature("plz7").set("linestyle", "dashed");
    model.result("pg10").feature("plz7").set("linewidth", 2);
    model.result("pg10").feature("plz7").set("display", "5");
    model.result("pg10").feature("plz7").create("col1", "Color");
    model.result("pg10").feature("plz7").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz7").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz8", "Polarization");
    model.result("pg10").feature("plz8").set("linestyle", "dashed");
    model.result("pg10").feature("plz8").set("linewidth", 2);
    model.result("pg10").feature("plz8").set("display", "6");
    model.result("pg10").feature("plz8").create("col1", "Color");
    model.result("pg10").feature("plz8").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz8").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz9", "Polarization");
    model.result("pg10").feature("plz9").set("linestyle", "dashed");
    model.result("pg10").feature("plz9").set("linewidth", 2);
    model.result("pg10").feature("plz9").set("display", "7");
    model.result("pg10").feature("plz9").create("col1", "Color");
    model.result("pg10").feature("plz9").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz9").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz10", "Polarization");
    model.result("pg10").feature("plz10").set("linestyle", "dashed");
    model.result("pg10").feature("plz10").set("linewidth", 2);
    model.result("pg10").feature("plz10").set("display", "9");
    model.result("pg10").feature("plz10").create("col1", "Color");
    model.result("pg10").feature("plz10").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz10").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz11", "Polarization");
    model.result("pg10").feature("plz11").set("linestyle", "dashed");
    model.result("pg10").feature("plz11").set("linewidth", 2);
    model.result("pg10").feature("plz11").set("display", "10");
    model.result("pg10").feature("plz11").create("col1", "Color");
    model.result("pg10").feature("plz11").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz11").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz12", "Polarization");
    model.result("pg10").feature("plz12").set("linestyle", "dashed");
    model.result("pg10").feature("plz12").set("linewidth", 2);
    model.result("pg10").feature("plz12").set("display", "11");
    model.result("pg10").feature("plz12").create("col1", "Color");
    model.result("pg10").feature("plz12").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz12").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz13", "Polarization");
    model.result("pg10").feature("plz13").set("linestyle", "dashed");
    model.result("pg10").feature("plz13").set("linewidth", 2);
    model.result("pg10").feature("plz13").set("display", "12");
    model.result("pg10").feature("plz13").create("col1", "Color");
    model.result("pg10").feature("plz13").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz13").feature("col1").set("colorlegend", false);
    model.result("pg10").create("plz14", "Polarization");
    model.result("pg10").feature("plz14").set("linestyle", "dashed");
    model.result("pg10").feature("plz14").set("linewidth", 2);
    model.result("pg10").feature("plz14").set("display", "13");
    model.result("pg10").feature("plz14").create("col1", "Color");
    model.result("pg10").feature("plz14").feature("col1").set("colortable", "Cyclic");
    model.result("pg10").feature("plz14").feature("col1").set("colorlegend", false);
    model.result("pg7").run();
    model.result("pg7").setIndex("looplevel", 11, 0);
    model.result("pg7").run();
    model.result("pg7").feature("mslc1").set("expr", "ewfd2.Ey");
    model.result("pg7").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg7").feature("mslc1").set("xcoord", "-d_rect_x/2 0 d_rect_x/2");
    model.result("pg7").feature("mslc1").set("ynumber", "0");
    model.result("pg7").feature("mslc1").set("colortable", "WaveLight");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();
    model.result("pg10").run();
    model.result("pg5").run();
    model.result("pg5").create("glob2", "Global");
    model.result("pg5").feature("glob2").set("markerpos", "datapoints");
    model.result("pg5").feature("glob2").set("linewidth", "preference");
    model.result("pg5").feature("glob2").set("data", "dset3");
    model.result("pg5").feature("glob2").setIndex("expr", "ewfd2.Rorder_0_0", 0);
    model.result("pg5").feature("glob2").setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6b21 [0,0]", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "ewfd2.Torder_0_0", 1);
    model.result("pg5").feature("glob2").setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [0,0]", 1);
    model.result("pg5").feature("glob2").setIndex("expr", "ewfd2.RTtotal", 2);
    model.result("pg5").feature("glob2").setIndex("expr", "ewfd2.Atotal", 3);
    model.result("pg5").feature("glob2").set("linestyle", "none");
    model.result("pg5").feature("glob2").set("linemarker", "cycle");
    model.result("pg5").run();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u65e0\u6548\u884d\u5c04\u7ea7");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Rorder_n1_0_ip", 0);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6b21 [-1,0]\uff0c\u9762\u5185", 0);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Rorder_n1_0_op", 1);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6b21 [-1,0]\uff0c\u9762\u5916", 1);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Rorder_p1_0_ip", 2);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6b21 [1,0]\uff0c\u9762\u5185", 2);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Rorder_p1_0_op", 3);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u53cd\u5c04\u7387\uff0c\u9636\u6b21 [1,0]\uff0c\u9762\u5916", 3);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_n1_0_ip", 4);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [-1,0]\uff0c\u9762\u5185", 4);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_n1_0_op", 5);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [-1,0]\uff0c\u9762\u5916", 5);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_p1_0_ip", 6);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [1,0]\uff0c\u9762\u5185", 6);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_p1_0_op", 7);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [1,0]\uff0c\u9762\u5916", 7);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_0_n1_ip", 8);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [0,-1]\uff0c\u9762\u5185", 8);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_0_n1_op", 9);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [0,-1]\uff0c\u9762\u5916", 9);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_0_p1_ip", 10);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [0,1]\uff0c\u9762\u5185", 10);
    model.result("pg11").feature("glob1").setIndex("expr", "ewfd2.Torder_0_p1_op", 11);
    model.result("pg11").feature("glob1")
         .setIndex("descr", "\u900f\u5c04\u7387\uff0c\u9636\u6b21 [0,1]\uff0c\u9762\u5916", 11);
    model.result("pg11").run();
    model.result().dataset().create("arr4", "Array3D");
    model.result().dataset("arr4").set("data", "dset3");
    model.result().dataset("arr4").set("fullsize", new int[]{5, 10, 1});
    model.result().create("pg12", "PlotGroup3D");
    model.result("pg12").run();
    model.result("pg12").label("\u77e9\u5f62\u9635\u5217\u7684\u573a\u56fe");
    model.result("pg12").set("data", "arr4");
    model.result("pg12").setIndex("looplevel", 10, 0);
    model.result("pg12").set("edges", false);
    model.result("pg12").create("vol1", "Volume");
    model.result("pg12").run();
    model.result("pg12").feature("vol1").set("expr", "ewfd2.Ey");
    model.result("pg12").feature("vol1").set("colortable", "WaveLight");
    model.result("pg12").feature("vol1").create("tran1", "Transparency");
    model.result("pg12").run();
    model.result("pg12").feature("vol1").feature("tran1").set("transparency", 0.2);
    model.result("pg12").run();
    model.result("pg12").create("vol2", "Volume");
    model.result("pg12").feature("vol2").set("expr", "1");
    model.result("pg12").feature("vol2").set("coloring", "uniform");
    model.result("pg12").feature("vol2").set("color", "gray");
    model.result("pg12").feature("vol2").set("resolution", "norefine");
    model.result("pg12").feature("vol2").create("sel1", "Selection");
    model.result("pg12").feature("vol2").feature("sel1").selection().set(5);
    model.result("pg12").run();
    model.result("pg12").run();

    model.title("\u516d\u8fb9\u5f62\u7b49\u79bb\u6fc0\u5143\u6ee4\u8272\u955c");

    model
         .description("\u575a\u56fa\u4e14\u6613\u4e8e\u5236\u9020\u7684\u6ee4\u8272\u955c\u6709\u7740\u5e7f\u6cdb\u7684\u5e94\u7528\u3002\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u57fa\u4e8e\u94dd\u8584\u5c42\u4e2d\u7684\u516d\u8fb9\u5f62\u5b54\u9635\u5217\u6765\u6784\u5efa\u5438\u6536\u6ee4\u8272\u955c\u6a21\u578b\u3002\u8fd9\u79cd\u7ed3\u6784\u4e3a\u516d\u8fb9\u5f62\u5468\u671f\u6027\u7ed3\u6784\uff0c\u4f46\u5728\u6a21\u578b\u4e2d\u6f14\u793a\u4e86\u5982\u4f55\u5c06\u95ee\u9898\u5b9a\u4e49\u4e3a\u77e9\u5f62\u5468\u671f\u6027\u95ee\u9898\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("hexagonal_plasmonic_color_filter.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}

/*
 * quadrupole.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class quadrupole {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mf", true);

    model.param().set("M", "11");
    model.param().descr("M", "\u79bb\u5b50\u8d28\u91cf\u6570");
    model.param().set("Z", "5");
    model.param().descr("Z", "\u79bb\u5b50\u7535\u8377\u6570");
    model.param().set("L1", "1[m]");
    model.param().descr("L1", "\u7b2c\u4e00\u4e2a\u56db\u6781\u533a\u957f\u5ea6");
    model.param().set("L2", "2[m]");
    model.param().descr("L2", "\u7b2c\u4e8c\u4e2a\u56db\u6781\u533a\u957f\u5ea6");
    model.param().set("L3", "1[m]");
    model.param().descr("L3", "\u7b2c\u4e09\u4e2a\u56db\u6781\u533a\u957f\u5ea6");
    model.param().set("vz", "0.01*c_const");
    model.param().descr("vz", "\u79bb\u5b50\u901f\u5ea6");
    model.param().set("m", "M*mp_const");
    model.param().descr("m", "\u79bb\u5b50\u8d28\u91cf");
    model.param().set("q", "Z*e_const");
    model.param().descr("q", "\u79bb\u5b50\u7535\u8377");
    model.param().set("Br", "8[mT]");
    model.param().descr("Br", "\u56db\u6781\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.177, 0.07});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, -0.035});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 45);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{0.2, 0.2});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("c1", "rot1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "90, 180, 270");
    model.component("comp1").geom("geom1").feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", 0.2);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", 0.12);
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("c2", "c3", "int1", "rot2");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "c2+c3-(int1+rot2(1)+rot2(2)+rot2(3))");
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", 0.2);
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").label("Iron");
    model.component("comp1").material("mat1").set("family", "iron");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"4000", "0", "0", "0", "4000", "0", "0", "0", "4000"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]", "0", "0", "0", "1.12e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]", "0", "0", "0", "12.2e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "440[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7870[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]", "0", "0", "0", "76.2[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "200[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.29");
    model.component("comp1").material("mat1").selection().set(2);
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u78c1\u4f53");
    model.component("comp1").material("mat2").selection().set(1, 3, 5, 6);
    model.component("comp1").material("mat2").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent_flux_density");
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05"});
    model.component("comp1").material("mat2").propertyGroup("RemanentFluxDensity").set("normBr", new String[]{"Br"});

    model.component("comp1").physics("mf").create("als1", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als1").selection().set(6);
    model.component("comp1").physics("mf").feature("als1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als1")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"-1/sqrt(2)", "-1/sqrt(2)", "0"});
    model.component("comp1").physics("mf").feature("als1").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als1").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("mf").create("als2", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als2").selection().set(3);
    model.component("comp1").physics("mf").feature("als2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als2")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"-1/sqrt(2)", "1/sqrt(2)", "0"});
    model.component("comp1").physics("mf").feature("als2").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als2").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("mf").create("als3", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als3").selection().set(1);
    model.component("comp1").physics("mf").feature("als3").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als3")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"1/sqrt(2)", "1/sqrt(2)", "0"});
    model.component("comp1").physics("mf").feature("als3").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als3").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("mf").create("als4", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als4").selection().set(5);
    model.component("comp1").physics("mf").feature("als4").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mf").feature("als4")
         .set("e_crel_BH_RemanentFluxDensity", new String[]{"1/sqrt(2)", "-1/sqrt(2)", "0"});
    model.component("comp1").physics("mf").feature("als4").set("sigma_mat", "userdef");
    model.component("comp1").physics("mf").feature("als4").set("epsilonr_mat", "userdef");
    model.component("comp1").physics("mf").create("als5", "AmperesLawSolid", 2);
    model.component("comp1").physics("mf").feature("als5").selection().set(2);

    model.component("comp1").mesh("mesh1").autoMeshSize(2);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.03);
    model.result("pg1").feature("str1").set("maxlen", 0.4);
    model.result("pg1").feature("str1").set("maxsteps", 5000);
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("inheritcolor", false);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("str1").set("data", "parent");
    model.result("pg1").feature("str1").selection().geom("geom1", 1);
    model.result("pg1").feature("str1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("solutionparams", "parent");
    model.result("pg1").feature("con1").set("expr", "mf.Az");
    model.result("pg1").feature("con1").set("titletype", "none");
    model.result("pg1").feature("con1").set("number", 10);
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("coloring", "uniform");
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("color", "custom");
    model.result("pg1").feature("con1")
         .set("customcolor", new double[]{0.3764705955982208, 0.3764705955982208, 0.3764705955982208});
    model.result("pg1").feature("con1").set("resolution", "fine");
    model.result("pg1").feature("con1").set("inheritcolor", false);
    model.result("pg1").feature("con1").set("showsolutionparams", "on");
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").feature("con1").feature().create("filt1", "Filter");
    model.result("pg1").feature("con1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "mf.normH");
    model.result("pg1").feature("surf1").set("descr", "\u78c1\u573a\u6a21");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u79bb\u5b50\u8f68\u8ff9");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "mf.normH");
    model.result("pg2").feature("surf1").set("descr", "\u78c1\u573a\u6a21");
    model.result("pg2").feature("surf1").set("colortable", "GrayBody");
    model.result("pg2").run();
    model.result("pg2").create("ptr1", "ParticleMass");
    model.result("pg2").feature("ptr1")
         .set("fx", "-q*vz*mf.By*(1-2*(partt>L1/vz)+2*(partt>(L1+L2)/vz)-(partt>(L1+L2+L3)/vz))");
    model.result("pg2").feature("ptr1")
         .set("fy", "q*vz*mf.Bx*(1-2*(partt>L1/vz)+2*(partt>(L1+L2)/vz)-(partt>(L1+L2+L3)/vz))");
    model.result("pg2").feature("ptr1").set("mass", "m");
    model.result("pg2").feature("ptr1").set("xcoord", "0.03*cos(range(0,0.05*pi,2*pi))");
    model.result("pg2").feature("ptr1").set("ycoord", "0.03*sin(range(0,0.05*pi,2*pi))");
    model.result("pg2").feature("ptr1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("ptr1").feature("col1")
         .set("expr", "1+(partt>L1/vz)+(partt>(L1+L2)/vz)+(partt>(L1+L2+L3)/vz)");
    model.result("pg2").feature("ptr1").feature("col1").set("colortable", "Cyclic");
    model.result("pg2").run();
    model.result("pg2").feature("ptr1").set("rtol", "1e-6");
    model.result("pg2").feature("ptr1").set("maxstepsactive", true);
    model.result("pg2").feature("ptr1").set("maxsteps", "1e5");
    model.result("pg2").feature("ptr1").set("statictendactive", true);
    model.result("pg2").feature("ptr1").set("statictend", "5/3e6");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").create("con1", "Contour");
    model.result("pg2").feature("con1").set("expr", "sqrt(x^2+y^2)");
    model.result("pg2").feature("con1").set("levelmethod", "levels");
    model.result("pg2").feature("con1").set("levels", "0.01 0.03");
    model.result("pg2").feature("con1").set("coloring", "uniform");
    model.result("pg2").feature("con1").set("color", "custom");
    model.result("pg2").feature("con1").set("colorlegend", false);
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u56db\u6781\u900f\u955c");

    model
         .description("\u4e0e\u5149\u5b66\u900f\u955c\u805a\u7126\u5149\u7c7b\u4f3c\uff0c\u7535\u78c1\u900f\u955c\u7528\u4e8e\u5e26\u7535\u7c92\u5b50\u675f\u7684\u805a\u7126\u3002COMSOL Multiphysics \u4e2d\u7684\u6a21\u578b\u663e\u793a B5+ \u79bb\u5b50\u901a\u8fc7\u4e00\u4e2a\u5305\u542b\u4e09\u6bb5\u56db\u6781\u900f\u955c\u7684\u805a\u7126\u7cfb\u7edf\u7684\u8def\u5f84\u3002");

    model.label("quadrupole.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

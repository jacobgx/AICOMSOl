/*
 * isotropic_compression.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:15 by COMSOL 6.3.0.290. */
public class isotropic_compression {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Geomechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.param().set("para", "0");
    model.param().descr("para", "\u53c2\u6570");
    model.param().set("p0", "200[kPa]");
    model.param().descr("p0", "\u538b\u529b");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "Pressure");
    model.func("int1").label("\u8fb9\u754c\u8f7d\u8377");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", 0.4, 1, 0);
    model.func("int1").setIndex("table", "2*p0", 1, 1);
    model.func("int1").setIndex("table", 0.6, 2, 0);
    model.func("int1").setIndex("table", "1*p0", 2, 1);
    model.func("int1").setIndex("table", 0.8, 3, 0);
    model.func("int1").setIndex("table", "2*p0", 3, 1);
    model.func("int1").setIndex("table", 1, 4, 0);
    model.func("int1").setIndex("table", "4*p0", 4, 1);
    model.func("int1").setIndex("argunit", 1, 0);
    model.func("int1").setIndex("fununit", "Pa", 0);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"5[cm]", "10[cm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "15[cm]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("epsm1", "ElastoplasticSoilMaterial", 2);
    model.component("comp1").physics("solid").feature("epsm1")
         .label("\u4fee\u6b63\u5251\u6865\u9ecf\u571f\u6a21\u578b (MCC)");
    model.component("comp1").physics("solid").feature("epsm1").selection().set(1);
    model.component("comp1").physics("solid").feature("epsm1").set("CamClayOption", "G");
    model.component("comp1").physics("solid").feature("epsm1").set("M_mat", "MatchtoMC");
    model.component("comp1").physics("solid").feature("epsm1").set("pc0", "300[kPa]");
    model.component("comp1").physics("solid").feature().duplicate("epsm2", "epsm1");
    model.component("comp1").physics("solid").feature("epsm2")
         .label("\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c\u6a21\u578b (BBMx)");
    model.component("comp1").physics("solid").feature("epsm2").selection().set(2);
    model.component("comp1").physics("solid").feature("epsm2").set("MaterialModel", "BarcelonaBasic");
    model.component("comp1").physics("solid").feature("epsm2").set("ss", 0);
    model.component("comp1").physics("solid").feature().duplicate("epsm3", "epsm1");
    model.component("comp1").physics("solid").feature("epsm3")
         .label("\u4fee\u6b63\u7684\u7ed3\u6784\u5316\u5251\u6865\u9ecf\u571f\u6a21\u578b (MSCC)");
    model.component("comp1").physics("solid").feature("epsm3").selection().set(3);
    model.component("comp1").physics("solid").feature("epsm3").set("MaterialModel", "StructuredCamClay");
    model.component("comp1").physics("solid").feature("epsm3").set("MS_mat", "MatchtoMC");
    model.component("comp1").physics("solid").feature("epsm3").set("pbi_mat", "userdef");
    model.component("comp1").physics("solid").feature("epsm3").set("zetaS_mat", "userdef");
    model.component("comp1").physics("solid").feature("epsm3").set("zetaS", 2);
    model.component("comp1").physics("solid").feature("epsm3").set("Deltaei_mat", "userdef");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u4fee\u6b63\u5251\u6865\u9ecf\u571f\u6a21\u578b 1");
    model.component("comp1").material("mat1").selection().set(1);
    model.component("comp1").material("mat1").propertyGroup().create("KG", "KG", "Bulk_modulus_and_shear_modulus");
    model.component("comp1").material("mat1").propertyGroup("KG").set("G", new String[]{"10[MPa]"});
    model.component("comp1").material("mat1").propertyGroup().create("CamClayModel", "CamClayModel", "Camclay");
    model.component("comp1").material("mat1").propertyGroup("CamClayModel")
         .set("kappaSwelling", new String[]{"0.013"});
    model.component("comp1").material("mat1").propertyGroup("CamClayModel").set("lambdaComp", new String[]{"0.032"});
    model.component("comp1").material("mat1").propertyGroup("CamClayModel").set("evoidref", new String[]{"0.7"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("MohrCoulomb", "MohrCoulomb", "Mohr_Coulomb_criterion");
    model.component("comp1").material("mat1").propertyGroup("MohrCoulomb")
         .set("internalphi", new String[]{"30[deg]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2400[kg/m^3]"});
    model.component("comp1").material().duplicate("mat2", "mat1");
    model.component("comp1").material("mat2")
         .label("\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c\u6a21\u578b\u6750\u6599");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material("mat2").propertyGroup()
         .create("BarcelonaBasicModel", "BarcelonaBasicModel", "Barcelona_Basic");
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel")
         .set("kappaSwelling", new String[]{"0.013"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel")
         .set("kappaSwellings", new String[]{"0.0013"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel")
         .set("lambdaComp0", new String[]{"0.032"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel")
         .set("lambdaCompss", new String[]{"0.0032"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel").set("wB", new String[]{"0.75"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel").set("mB", new String[]{"1e4"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel").set("bB", new String[]{"100"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel").set("kB", new String[]{"0.6"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel")
         .set("evoidref0", new String[]{"0.7"});
    model.component("comp1").material("mat2").propertyGroup("BarcelonaBasicModel")
         .set("sy0", new String[]{"0.3[MPa]"});
    model.component("comp1").material().duplicate("mat3", "mat2");
    model.component("comp1").material("mat3")
         .label("\u4fee\u6b63\u7684\u7ed3\u6784\u5316\u5251\u6865\u9ecf\u571f\u6a21\u578b\u6750\u6599 3");
    model.component("comp1").material("mat3").selection().set(3);
    model.component("comp1").material("mat3").propertyGroup()
         .create("StructuredCamClayModel", "StructuredCamClayModel", "Structured_Camclay");
    model.component("comp1").material("mat3").propertyGroup("StructuredCamClayModel")
         .set("kappaSwellingS", new String[]{"0.013"});
    model.component("comp1").material("mat3").propertyGroup("StructuredCamClayModel")
         .set("lambdaCompS", new String[]{"0.032"});
    model.component("comp1").material("mat3").propertyGroup("StructuredCamClayModel")
         .set("evoidrefS", new String[]{"0.7"});
    model.component("comp1").material("mat3").propertyGroup("StructuredCamClayModel").set("dvS", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("StructuredCamClayModel").set("dsS", new String[]{"1"});
    model.component("comp1").material("mat3").propertyGroup("StructuredCamClayModel")
         .set("epdevc", new String[]{"0.02"});

    model.component("comp1").physics("solid").create("tm1", "TestMaterial", -1);
    model.component("comp1").physics("solid").feature("tm1")
         .label("\u6d4b\u8bd5\u6750\u6599 [\u4fee\u6b63\u5251\u6865\u9ecf\u571f\u6a21\u578b]");
    model.component("comp1").physics("solid").feature("tm1").selection("domainSelection").set(1);
    model.component("comp1").physics("solid").feature("tm1").set("stepN", 100);
    model.component("comp1").physics("solid").feature("tm1").set("testSetup", "userDefined");
    model.component("comp1").physics("solid").feature("tm1").set("testControl", "force");
    model.component("comp1").physics("solid").feature("tm1").set("uniaxialTest", false);
    model.component("comp1").physics("solid").feature("tm1").set("isotropicTest", true);
    model.component("comp1").physics("solid").feature("tm1").set("forceFunction4", "Pressure(para)");
    model.component("comp1").physics("solid").feature("tm1").runCommand("setupTests");
    model.component("comp1").physics("solid").feature().duplicate("tm2", "tm1");
    model.component("comp1").physics("solid").feature("tm2")
         .label("\u6d4b\u8bd5\u6750\u6599 [\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c\u6a21\u578b]");
    model.component("comp1").physics("solid").feature("tm2").selection("domainSelection").set(2);
    model.component("comp1").physics("solid").feature("tm2").runCommand("setupTests");
    model.component("comp1").physics("solid").feature().duplicate("tm3", "tm1");
    model.component("comp1").physics("solid").feature("tm3")
         .label("\u6d4b\u8bd5\u6750\u6599 [\u4fee\u6b63\u7684\u7ed3\u6784\u5316\u5251\u6865\u9ecf\u571f\u6a21\u578b]");
    model.component("comp1").physics("solid").feature("tm3").selection("domainSelection").set(3);
    model.component("comp1").physics("solid").feature("tm3").runCommand("setupTests");

    model.result().dataset().remove("dset1");
    model.result().dataset().remove("dset2");
    model.result().dataset().remove("dset3");
    model.result().dataset().remove("dset5");
    model.result().dataset().remove("dset6");
    model.result().dataset().remove("dset7");
    model.result().dataset().remove("dset8");
    model.result().dataset().remove("dset9");
    model.result().dataset().remove("dset11");
    model.result().dataset().remove("dset12");
    model.result().dataset().remove("dset13");
    model.result().dataset().remove("dset14");
    model.result().dataset().remove("dset15");
    model.result().dataset().remove("dset16");
    model.result().dataset().remove("dset17");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u7a7a\u9699\u6bd4 (MCC)");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u538b\u529b (kPa)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u7a7a\u9699\u6bd4 (1)");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 95);
    model.result("pg1").set("xmax", 1300);
    model.result("pg1").set("ymin", 0.62);
    model.result("pg1").set("ymax", 0.706);
    model.result("pg1").set("xlog", true);
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(1);
    model.result("pg1").feature("ptgr1").set("expr", "solid1.epsm1.evoid");
    model.result("pg1").feature("ptgr1").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "solid1.pmGp");
    model.result("pg1").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg1").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("data", "dset4");
    model.result("pg1").feature("ann1").setIndex("looplevel", 1, 0);
    model.result("pg1").feature("ann1").set("text", "p=eval(at3(0,0,0,solid1.pm), kPa) kPa");
    model.result("pg1").feature("ann1").set("level", "global");
    model.result("pg1").feature("ann1").set("posxexpr", "at3(0,0,0,solid1.pm)/1000");
    model.result("pg1").feature("ann1").set("posyexpr", "at3(0,0,0,solid1.epsm1.evoid)");
    model.result("pg1").feature("ann1").set("exprprecision", 3);
    model.result("pg1").feature("ann1").set("anchorpoint", "lowerleft");
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").setIndex("looplevel", 21, 0);
    model.result("pg1").feature().duplicate("ann3", "ann2");
    model.result("pg1").run();
    model.result("pg1").feature("ann3").setIndex("looplevel", 41, 0);
    model.result("pg1").feature().duplicate("ann4", "ann3");
    model.result("pg1").run();
    model.result("pg1").feature("ann4").setIndex("looplevel", 61, 0);
    model.result("pg1").feature("ann4").set("anchorpoint", "lowerright");
    model.result("pg1").feature().duplicate("ann5", "ann4");
    model.result("pg1").run();
    model.result("pg1").feature("ann5").setIndex("looplevel", 101, 0);
    model.result("pg1").feature("ann5").set("anchorpoint", "lowerleft");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2")
         .set("expr", "solid1.epsm1.evoidref-solid1.epsm1.lambdaComp*log(solid1.epsm1.p/solid1.epsm1.pref)");
    model.result("pg1").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg1").run();
    model.result("pg1").create("tlan1", "TableAnnotation");
    model.result("pg1").feature("tlan1").set("source", "localtable");
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 160, 0, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.689, 0, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u6807\u51c6\u538b\u7f29\u7ebf", 0, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 145, 1, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.67, 1, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u81a8\u80c0\u7ebf", 1, 2);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 255, 2, 0);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", 0.653, 2, 1);
    model.result("pg1").feature("tlan1").setIndex("localtablematrix", "\u81a8\u80c0\u7ebf", 2, 2);
    model.result("pg1").feature("tlan1").set("showpoint", false);
    model.result("pg1").feature("tlan1").set("showframe", true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7a7a\u9699\u6bd4 (MCC)\uff0c\u6570\u503c\u89e3 Vs. \u89e3\u6790\u89e3");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u538b\u529b (kPa)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7a7a\u9699\u6bd4 (1)");
    model.result("pg2").set("xlog", true);
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "solid1.epsm1.evoid");
    model.result("pg2").feature("ptgr1").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg2").feature("ptgr1").set("xdataexpr", "solid1.pmGp");
    model.result("pg2").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg2").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u6570\u503c\u89e3", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2")
         .set("expr", "solid1.epsm1.evoidref-(solid1.epsm1.lambdaComp-solid1.epsm1.kappaSwelling)*log(solid1.epsm1.pc/solid1.epsm1.pref)-solid1.epsm1.kappaSwelling*log(solid1.epsm1.p/solid1.epsm1.pref)");
    model.result("pg2").feature("ptgr2").set("linestyle", "dashed");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7a7a\u9699\u6bd4");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u538b\u529b (kPa)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7a7a\u9699\u6bd4 (1)");
    model.result("pg3").set("xlog", true);
    model.result("pg3").create("ptgr1", "PointGraph");
    model.result("pg3").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr1").set("linewidth", "preference");
    model.result("pg3").feature("ptgr1").selection().set(1);
    model.result("pg3").feature("ptgr1").set("expr", "solid1.epsm1.evoid");
    model.result("pg3").feature("ptgr1").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg3").feature("ptgr1").set("xdataexpr", "solid1.pmGp");
    model.result("pg3").feature("ptgr1").set("xdatadescr", "\u538b\u529b");
    model.result("pg3").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg3").feature("ptgr1").set("linecolor", "red");
    model.result("pg3").feature("ptgr1").set("linemarker", "asterisk");
    model.result("pg3").feature("ptgr1").set("markerpos", "interp");
    model.result("pg3").feature("ptgr1").set("legend", true);
    model.result("pg3").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr1").setIndex("legends", "MCC", 0);
    model.result("pg3").run();
    model.result("pg3").create("ptgr2", "PointGraph");
    model.result("pg3").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr2").set("linewidth", "preference");
    model.result("pg3").feature("ptgr2").set("data", "dset10");
    model.result("pg3").feature("ptgr2").selection().set(1);
    model.result("pg3").feature("ptgr2").set("expr", "solid2.epsm2.evoid");
    model.result("pg3").feature("ptgr2").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg3").feature("ptgr2").set("xdataexpr", "solid2.pmGp");
    model.result("pg3").feature("ptgr2").set("xdatadescr", "\u538b\u529b");
    model.result("pg3").feature("ptgr2").set("xdataunit", "kPa");
    model.result("pg3").feature("ptgr2").set("linecolor", "green");
    model.result("pg3").feature("ptgr2").set("linemarker", "circle");
    model.result("pg3").feature("ptgr2").set("markerpos", "interp");
    model.result("pg3").feature("ptgr2").set("markers", 10);
    model.result("pg3").feature("ptgr2").set("legend", true);
    model.result("pg3").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr2").setIndex("legends", "BBMx", 0);
    model.result("pg3").run();
    model.result("pg3").create("ptgr3", "PointGraph");
    model.result("pg3").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg3").feature("ptgr3").set("linewidth", "preference");
    model.result("pg3").feature("ptgr3").set("data", "dset18");
    model.result("pg3").feature("ptgr3").selection().set(1);
    model.result("pg3").feature("ptgr3").set("expr", "solid3.epsm3.evoid");
    model.result("pg3").feature("ptgr3").set("descr", "\u7a7a\u9699\u6bd4");
    model.result("pg3").feature("ptgr3").set("xdataexpr", "solid3.pmGp");
    model.result("pg3").feature("ptgr3").set("xdatadescr", "\u538b\u529b");
    model.result("pg3").feature("ptgr3").set("xdataunit", "kPa");
    model.result("pg3").feature("ptgr3").set("linecolor", "blue");
    model.result("pg3").feature("ptgr3").set("linemarker", "diamond");
    model.result("pg3").feature("ptgr3").set("markerpos", "interp");
    model.result("pg3").feature("ptgr3").set("markers", 12);
    model.result("pg3").feature("ptgr3").set("legend", true);
    model.result("pg3").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg3").feature("ptgr3").setIndex("legends", "MSCC", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u56fa\u7ed3\u538b\u529b vs. \u8fb9\u754c\u8f7d\u8377");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8fb9\u754c\u8f7d\u8377 (kPa)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u56fa\u7ed3\u538b\u529b (kPa)");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(1);
    model.result("pg4").feature("ptgr1").set("expr", "solid1.epsm1.pc");
    model.result("pg4").feature("ptgr1").set("descr", "\u56fa\u7ed3\u538b\u529b");
    model.result("pg4").feature("ptgr1").set("unit", "kPa");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "Pressure(para)");
    model.result("pg4").feature("ptgr1").set("xdataunit", "kPa");
    model.result("pg4").feature("ptgr1").set("linecolor", "red");
    model.result("pg4").feature("ptgr1").set("linemarker", "asterisk");
    model.result("pg4").feature("ptgr1").set("markerpos", "interp");
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "MCC", 0);
    model.result("pg4").run();
    model.result("pg4").create("ptgr2", "PointGraph");
    model.result("pg4").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr2").set("linewidth", "preference");
    model.result("pg4").feature("ptgr2").set("data", "dset10");
    model.result("pg4").feature("ptgr2").selection().set(1);
    model.result("pg4").feature("ptgr2").set("expr", "solid2.epsm2.pc");
    model.result("pg4").feature("ptgr2")
         .set("descr", "\u9971\u548c\u72b6\u6001\u4e0b\u7684\u56fa\u7ed3\u538b\u529b");
    model.result("pg4").feature("ptgr2").set("unit", "kPa");
    model.result("pg4").feature("ptgr2").set("xdata", "expr");
    model.result("pg4").feature("ptgr2").set("xdataexpr", "Pressure(para)");
    model.result("pg4").feature("ptgr2").set("xdataunit", "kPa");
    model.result("pg4").feature("ptgr2").set("linecolor", "green");
    model.result("pg4").feature("ptgr2").set("linemarker", "circle");
    model.result("pg4").feature("ptgr2").set("markerpos", "interp");
    model.result("pg4").feature("ptgr2").set("markers", 10);
    model.result("pg4").feature("ptgr2").set("legend", true);
    model.result("pg4").feature("ptgr2").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr2").setIndex("legends", "BBMx", 0);
    model.result("pg4").run();
    model.result("pg4").create("ptgr3", "PointGraph");
    model.result("pg4").feature("ptgr3").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr3").set("linewidth", "preference");
    model.result("pg4").feature("ptgr3").set("data", "dset18");
    model.result("pg4").feature("ptgr3").selection().set(1);
    model.result("pg4").feature("ptgr3").set("expr", "solid3.epsm3.pc");
    model.result("pg4").feature("ptgr3").set("descr", "\u56fa\u7ed3\u538b\u529b");
    model.result("pg4").feature("ptgr3").set("unit", "kPa");
    model.result("pg4").feature("ptgr3").set("xdata", "expr");
    model.result("pg4").feature("ptgr3").set("xdataexpr", "Pressure(para)");
    model.result("pg4").feature("ptgr3").set("xdataunit", "kPa");
    model.result("pg4").feature("ptgr3").set("linecolor", "blue");
    model.result("pg4").feature("ptgr3").set("linemarker", "diamond");
    model.result("pg4").feature("ptgr3").set("markerpos", "interp");
    model.result("pg4").feature("ptgr3").set("markers", 12);
    model.result("pg4").feature("ptgr3").set("legend", true);
    model.result("pg4").feature("ptgr3").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr3").setIndex("legends", "MSCC", 0);
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u6700\u7ec8\u7a7a\u9699\u6bd4");
    model.result().evaluationGroup("eg1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg1").feature("pev1").selection().set(1);
    model.result().evaluationGroup("eg1").feature("pev1").set("expr", new String[]{"solid1.epsm1.evoid"});
    model.result().evaluationGroup("eg1").feature("pev1").set("descr", new String[]{"\u7a7a\u9699\u6bd4"});
    model.result().evaluationGroup("eg1").feature("pev1").set("unit", new String[]{"1"});
    model.result().evaluationGroup("eg1").feature("pev1").setIndex("descr", "\u7a7a\u9699\u6bd4\uff0cMCC", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev2", "pev1");
    model.result().evaluationGroup("eg1").feature("pev2").set("data", "dset10");
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("eg1").feature("pev2").selection().set(1);
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("expr", "solid2.epsm2.evoid", 0);
    model.result().evaluationGroup("eg1").feature("pev2").setIndex("descr", "\u7a7a\u9699\u6bd4\uff0cBBMx", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("pev3", "pev2");
    model.result().evaluationGroup("eg1").feature("pev3").set("data", "dset18");
    model.result().evaluationGroup("eg1").feature("pev3").selection().set(1);
    model.result().evaluationGroup("eg1").feature("pev3").setIndex("expr", "solid3.epsm3.evoid", 0);
    model.result().evaluationGroup("eg1").feature("pev3").setIndex("descr", "\u7a7a\u9699\u6bd4\uff0cMSCC", 0);
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").run();
    model.result("pg4").run();

    model
         .title("\u4f7f\u7528\u4fee\u6b63\u5251\u6865\u9ecf\u571f\u6750\u6599\u6a21\u578b\u6a21\u62df\u7b49\u5411\u538b\u7f29\u8bd5\u9a8c");

    model
         .description("\u672c\u6a21\u578b\u793a\u4f8b\u4f7f\u7528\u4fee\u6b63\u5251\u6865\u9ecf\u571f (MCC) \u6a21\u578b\u3001\u6269\u5c55\u5df4\u585e\u7f57\u90a3\u57fa\u672c (BBMx) \u6a21\u578b\u548c\u4fee\u6b63\u7684\u7ed3\u6784\u5316\u5251\u6865\u9ecf\u571f (MSCC) \u6750\u6599\u6a21\u578b\u6765\u6a21\u62df\u571f\u6837\u7684\u7b49\u5411\u538b\u7f29\uff0c\u76ee\u7684\u662f\u91cd\u73b0\u89e3\u6790\u66f2\u7ebf\uff0c\u4ee5\u663e\u793a\u6b63\u5e38\u56fa\u7ed3\u7ebf (NCL) \u548c\u56de\u5f39\u7ebf\uff08\u6216\u5378\u8f7d/\u518d\u52a0\u8f7d\u7ebf (URL)\uff09\u3002");

    model.mesh().clearMeshes();

    model.sol("solidtm1sol").clearSolutionData();
    model.sol("solidtm1sol1").clearSolutionData();
    model.sol("sol1").clearSolutionData();
    model.sol("solidtm2sol").clearSolutionData();
    model.sol("solidtm2sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("solidtm3sol").clearSolutionData();
    model.sol("solidtm3sol1").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("isotropic_compression.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

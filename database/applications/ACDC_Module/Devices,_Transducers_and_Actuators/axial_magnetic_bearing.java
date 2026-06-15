/*
 * axial_magnetic_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class axial_magnetic_bearing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Transducers_and_Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mfnc", "MagnetostaticsNoCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mfnc", true);

    model.param().set("R1", "10[mm]");
    model.param().descr("R1", "\u5185\u78c1\u4f53\u7684\u5185\u534a\u5f84");
    model.param().set("R2", "20[mm]");
    model.param().descr("R2", "\u5185\u78c1\u4f53\u7684\u5916\u534a\u5f84");
    model.param().set("R3", "22[mm]");
    model.param().descr("R3", "\u5916\u78c1\u4f53\u7684\u5185\u534a\u5f84");
    model.param().set("R4", "32[mm]");
    model.param().descr("R4", "\u5916\u78c1\u4f53\u7684\u5916\u534a\u5f84");
    model.param().set("h0", "10[mm]");
    model.param().descr("h0", "\u78c1\u4f53\u9ad8\u5ea6");
    model.param().set("Br", "1[T]");
    model.param().descr("Br", "\u78c1\u4f53\u7684\u5269\u4f59\u78c1\u901a\u5bc6\u5ea6");
    model.param().set("dZ", "0[mm]");
    model.param().descr("dZ", "\u8f74\u5411\u4f4d\u79fb");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"R2-R1", "h0*3"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"R1", "-h0/2-h0+dZ"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "h0", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"R4-R3", "h0*3"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("pos", "R3", 0);
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"R3", "-h0/2-h0"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{70, 160});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{0, -80});
    model.component("comp1").geom("geom1").feature("r3").setIndex("layer", 5, 0);
    model.component("comp1").geom("geom1").feature("r3").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r3").set("layertop", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r1", 1, 4, 5, 8);
    model.component("comp1").geom("geom1").feature("fil1").selection("pointinsketch").set("r2", 1, 4, 5, 8);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 2);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").coordSystem().create("ie1", "InfiniteElement");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("ie1").selection().set(1, 3, 10, 11, 12);
    model.component("comp1").coordSystem("ie1").set("ScalingType", "Cylindrical");

    model.component("comp1").physics("mfnc").create("mfcs1", "MagneticFluxConservationSolid", 2);
    model.component("comp1").physics("mfnc").feature("mfcs1").selection().set(6, 9);
    model.component("comp1").physics("mfnc").feature("mfcs1").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfnc").feature("mfcs1")
         .set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, -1});
    model.component("comp1").physics("mfnc").create("mfcs2", "MagneticFluxConservationSolid", 2);
    model.component("comp1").physics("mfnc").feature("mfcs2").selection().set(4, 7);
    model.component("comp1").physics("mfnc").feature("mfcs2").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfnc").feature("mfcs2")
         .set("e_crel_BH_RemanentFluxDensity", new int[]{0, 0, 1});
    model.component("comp1").physics("mfnc").create("mfcs3", "MagneticFluxConservationSolid", 2);
    model.component("comp1").physics("mfnc").feature("mfcs3").selection().set(5);
    model.component("comp1").physics("mfnc").feature("mfcs3").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfnc").create("mfcs4", "MagneticFluxConservationSolid", 2);
    model.component("comp1").physics("mfnc").feature("mfcs4").selection().set(8);
    model.component("comp1").physics("mfnc").feature("mfcs4").set("ConstitutiveRelationBH", "RemanentFluxDensity");
    model.component("comp1").physics("mfnc").feature("mfcs4")
         .set("e_crel_BH_RemanentFluxDensity", new int[]{-1, 0, 0});

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RemanentFluxDensity", "RemanentFluxDensity", "Remanent flux density");
    model.component("comp1").material("mat1").label("N50 (Sintered NdFeB)");
    model.component("comp1").material("mat1").set("family", "chrome");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]", "0", "0", "0", "1/1.4[uohm*m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity").label("Remanent flux density");
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity")
         .set("murec", new String[]{"1.05", "0", "0", "0", "1.05", "0", "0", "0", "1.05"});
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity").set("normBr", "1.41[T]");
    model.component("comp1").material("mat1").selection().set(4, 5, 6, 7, 8, 9);
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity").set("murec", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("RemanentFluxDensity").set("normBr", new String[]{"Br"});
    model.component("comp1").material("mat1").label("\u901a\u7528\u78c1\u4f53");

    model.component("comp1").physics("mfnc").create("fcal1", "ForceCalculation", 2);
    model.component("comp1").physics("mfnc").feature("fcal1").selection().set(4, 5, 6);
    model.component("comp1").physics("mfnc").create("zsp1", "ZeroMagneticScalarPotential", 0);
    model.component("comp1").physics("mfnc").feature("zsp1").selection().set(30);
    model.component("comp1").physics().create("sens", "Sensitivity", "geom1");

    model.study("std1").feature("stat").setSolveFor("/physics/sens", true);

    model.component("comp1").physics("sens").create("gcvar1", "GlobalControlVariables", -1);
    model.component("comp1").physics("sens").feature("gcvar1").setIndex("variableList", "dZ", 0, 0);
    model.component("comp1").physics("sens").create("gobj1", "GlobalObjective", -1);
    model.component("comp1").physics("sens").feature("gobj1").set("objectiveExpression", "mfnc.Forcez_0");

    model.component("comp1").common().create("free1", "DeformingDomain");
    model.component("comp1").common("free1").selection().all();
    model.component("comp1").common("free1").selection().set(2);
    model.component("comp1").common("free1").set("smoothingType", "laplace");
    model.component("comp1").common().create("pres1", "PrescribedDeformation");
    model.component("comp1").common("pres1").selection().set(4, 5, 6);
    model.component("comp1").common("pres1").set("prescribedDeformation", new String[]{"0", "0", "dZ"});
    model.component("comp1").common().create("disp1", "PrescribedMeshDisplacement");
    model.component("comp1").common("disp1").selection()
         .set(3, 4, 6, 18, 19, 21, 23, 24, 25, 26, 27, 30, 42, 43, 44, 45);

    model.component("comp1").mesh("mesh1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "R1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "dZ", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(-40,2,40)", 0);
    model.study("std1").setGenPlots(false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").create("sn1", "Sensitivity");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").set("solution", "sol27");
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().geom("geom1", 2);
    model.result().dataset("dset3").selection().set(4, 5, 6, 7, 8, 9);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "dset3");
    model.result().dataset("rev1").set("startangle", -100);
    model.result().dataset("rev1").set("revangle", 280);
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").run();
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", "mfnc.normB");
    model.result("pg1").feature("surf1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("str1", "Streamline");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
    model.result("pg1").feature("str1").set("color", "gray");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("data", "dset2");
    model.result("pg2").feature("glob1").set("expr", new String[]{});
    model.result("pg2").feature("glob1").set("descr", new String[]{});
    model.result("pg2").feature("glob1").set("expr", new String[]{"sens.gobj1"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u76ee\u6807\u503c"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("data", "dset2");
    model.result("pg3").feature("glob1").set("expr", new String[]{});
    model.result("pg3").feature("glob1").set("descr", new String[]{});
    model.result("pg3").feature("glob1").setIndex("expr", "fsens(dZ)", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "N/m", 0);
    model.result("pg3").feature("glob1").setIndex("descr", "Fz \u76f8\u5bf9\u4e8e dZ \u7684\u7075\u654f\u5ea6", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").run();
    model.result("pg4").create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("expr", "mfnc.normB");
    model.result("pg4").feature("vol1").set("descr", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.result("pg4").run();

    model.title("\u4f7f\u7528\u6c38\u78c1\u4f53\u7684\u8f74\u5411\u78c1\u8f74\u627f");

    model
         .description("\u6c38\u4e45\u78c1\u8f74\u627f\u5e7f\u6cdb\u5e94\u7528\u4e8e\u900f\u5e73\u673a\u3001\u6cf5\u3001\u7535\u52a8\u673a\u3001\u53d1\u7535\u673a\u4ee5\u53ca\u98de\u8f6e\u80fd\u91cf\u5b58\u50a8\u7cfb\u7edf\u7b49\u7b49\u3002\u4e0e\u4f20\u7edf\u8f74\u627f\u76f8\u6bd4\uff0c\u5b83\u5177\u6709\u65e0\u63a5\u89e6\u64cd\u4f5c\u3001\u4f4e\u7ef4\u62a4\u4ee5\u53ca\u65e0\u9700\u6da6\u6ed1\u7b49\u7a81\u51fa\u4f18\u70b9\u3002\u672c\u4f8b\u63cf\u8ff0\u5982\u4f55\u8ba1\u7b97\u8f74\u5411\u6c38\u4e45\u78c1\u8f74\u627f\u7684\u8bbe\u8ba1\u53c2\u6570\uff0c\u5982\u78c1\u529b\u548c\u521a\u5ea6\u7b49\u3002");

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

    model.label("axial_magnetic_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

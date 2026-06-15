/*
 * pacemaker_electrode_llcreop.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:45 by COMSOL 6.3.0.290. */
public class pacemaker_electrode_llcreop {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\LiveLink_for_PTC_Creo_Parametric\\Tutorials,_LiveLink_Interface");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1").create("cad1", "LiveLinkPTCCreoParametric");
    model.component("comp1").geom("geom1").feature("cad1").updateCadParamTable(false, false);
    model.component("comp1").geom("geom1").feature("cad1").importData();

    model.component("comp1").view("view1").set("transparency", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("el_cond", "0.4[S/m]", "Electric conductivity of heart tissue");
    model.param().set("rel_perm", "1", "Relative permittivity of heart tissue");
    model.param().set("Vtot", "1[V]", "Applied voltage");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "my_int");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().named("geom1_cad1_Counter_electrode_bnd");
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"el_cond"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"rel_perm"});
    model.component("comp1").material("mat1").label("\u6750\u6599 1");

    model.component("comp1").physics("ec").create("gnd1", "Ground", 2);
    model.component("comp1").physics("ec").feature("gnd1").selection().named("geom1_cad1_Counter_electrode_bnd");
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 2);
    model.component("comp1").physics("ec").feature("pot1").selection().named("geom1_cad1_Working_electrode_bnd");
    model.component("comp1").physics("ec").feature("pot1").set("V0", "Vtot");

    model.component("comp1").mesh("mesh1").autoMeshSize(4);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("solutionparams", "parent");
    model.result("pg1").feature("vol1").set("colortable", "Dipole");
    model.result("pg1").feature("vol1").set("showsolutionparams", "on");
    model.result("pg1").feature("vol1").set("data", "parent");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "ec.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg2").feature("strmsl1").set("titletype", "none");
    model.result("pg2").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg2").feature("strmsl1").set("udist", 0.02);
    model.result("pg2").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg2").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("inheritcolor", false);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("strmsl1").set("data", "parent");
    model.result("pg2").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg2").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "-Vtot/my_int(reacf(V))[A]", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").create("sel1", "Selection");
    model.result("pg3").feature("surf1").feature("sel1").selection().named("geom1_cad1_Pacemaker_bnd");
    model.result("pg3").run();
    model.result("pg3").create("str1", "Streamline");
    model.result("pg3").feature("str1").set("posmethod", "start");
    model.result("pg3").feature("str1").set("color", "blue");
    model.result("pg3").run();

    model.component("comp1").view("view1").set("transparency", false);

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "LL_d36", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "LL_d36", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(8.5[mm],1[mm],12.5[mm])", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u52bf (ec) 1");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 5, 0);
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("vol1", "Volume");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("solutionparams", "parent");
    model.result("pg4").feature("vol1").set("colortable", "Dipole");
    model.result("pg4").feature("vol1").set("showsolutionparams", "on");
    model.result("pg4").feature("vol1").set("data", "parent");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u7535\u573a (ec) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 5, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("solutionparams", "parent");
    model.result("pg5").feature("mslc1").set("expr", "ec.normE");
    model.result("pg5").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("mslc1").set("xcoord", "ec.CPx");
    model.result("pg5").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("mslc1").set("ycoord", "ec.CPy");
    model.result("pg5").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("mslc1").set("zcoord", "ec.CPz");
    model.result("pg5").feature("mslc1").set("colortable", "Prism");
    model.result("pg5").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg5").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg5").feature("mslc1").set("data", "parent");
    model.result("pg5").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg5").feature("strmsl1").set("expr", new String[]{"ec.Ex", "ec.Ey", "ec.Ez"});
    model.result("pg5").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg5").feature("strmsl1").set("xcoord", "ec.CPx");
    model.result("pg5").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg5").feature("strmsl1").set("ycoord", "ec.CPy");
    model.result("pg5").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg5").feature("strmsl1").set("zcoord", "ec.CPz");
    model.result("pg5").feature("strmsl1").set("titletype", "none");
    model.result("pg5").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg5").feature("strmsl1").set("udist", 0.02);
    model.result("pg5").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg5").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("inheritcolor", false);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg5").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("strmsl1").set("data", "parent");
    model.result("pg5").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg5").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg5").feature("strmsl1").feature("col1").set("expr", "ec.normE");
    model.result("pg5").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg5").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg5").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg5").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result().evaluationGroup("eg1").set("data", "dset2");
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "none");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg6").feature("tblp1").set("evaluationgroup", "eg1");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").run();
    model.result("pg7").set("data", "dset2");
    model.result("pg7").setIndex("looplevel", 3, 0);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").create("sel1", "Selection");
    model.result("pg7").feature("surf1").feature("sel1").selection().named("geom1_cad1_Pacemaker_bnd");
    model.result("pg7").run();
    model.result("pg7").create("str1", "Streamline");
    model.result("pg7").feature("str1").set("posmethod", "start");
    model.result("pg7").feature("str1").set("color", "blue");
    model.result("pg7").run();

    model.title("\u8d77\u640f\u5668\u7535\u6781");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5fc3\u810f\u8d77\u640f\u5668\u7535\u6781\u5468\u56f4\u7684\u7535\u6d41\u5206\u5e03\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("pacemaker_electrode_llcreop.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

/*
 * circuit_fem_resistor.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class circuit_fem_resistor {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Circuits");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ec", true);
    model.study("std1").feature("stat").setSolveFor("/physics/cir", true);

    model.param().set("sigma", "1e3[S/m]");
    model.param().set("R1", "1[\u03a9]");
    model.param().set("R2", "1[\u03a9]");
    model.param().set("L", "5[mm]");
    model.param().set("r", "1[mm]");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").physics("ec").create("term1", "Terminal", 2);
    model.component("comp1").physics("ec").feature("term1").selection().set(4);
    model.component("comp1").physics("ec").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("ec").create("term2", "Terminal", 2);
    model.component("comp1").physics("ec").feature("term2").selection().set(3);
    model.component("comp1").physics("ec").feature("term2").set("TerminalType", "Circuit");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R1");
    model.component("comp1").physics("cir").create("termI1", "ModelTerminalIV", -1);
    model.component("comp1").physics("cir").feature("termI1").set("V_src", "root.comp1.ec.V0_1");
    model.component("comp1").physics("cir").feature("termI1").set("Connections", 1);
    model.component("comp1").physics("cir").create("termI2", "ModelTerminalIV", -1);
    model.component("comp1").physics("cir").feature("termI2").set("V_src", "root.comp1.ec.V0_2");
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "R2");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 3, 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("mm");
    model.component("comp2").geom("geom2").create("cyl1", "Cylinder");
    model.component("comp2").geom("geom2").feature("cyl1").set("r", "r");
    model.component("comp2").geom("geom2").feature("cyl1").set("h", "L");

    model.component("comp2").physics().create("ec2", "ConductiveMedia", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ec2", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics().create("cir2", "Circuit", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/cir2", true);

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"sigma"});
    model.component("comp2").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp2").physics("ec2").create("term1", "Terminal", 2);
    model.component("comp2").physics("ec2").feature("term1").selection().set(4);
    model.component("comp2").physics("ec2").feature("term1").set("TerminalType", "Circuit");
    model.component("comp2").physics("ec2").create("gnd1", "Ground", 2);
    model.component("comp2").physics("ec2").feature("gnd1").selection().set(3);
    model.component("comp2").physics("cir2").create("R1", "Resistor", -1);
    model.component("comp2").physics("cir2").feature("R1").setIndex("Connections", 0, 1, 0);
    model.component("comp2").physics("cir2").feature("R1").set("R", "R1");
    model.component("comp2").physics("cir2").create("IvsU1", "ModelDeviceIV", -1);
    model.component("comp2").physics("cir2").feature("IvsU1").setIndex("Connections", 1, 0, 0);
    model.component("comp2").physics("cir2").feature("IvsU1").setIndex("Connections", 2, 1, 0);
    model.component("comp2").physics("cir2").feature("IvsU1").set("V_src", "root.comp2.ec2.V0_1");
    model.component("comp2").physics("cir2").create("R2", "Resistor", -1);
    model.component("comp2").physics("cir2").feature("R2").setIndex("Connections", 2, 1, 0);
    model.component("comp2").physics("cir2").feature("R2").set("R", "R2");
    model.component("comp2").physics("cir2").create("V1", "VoltageSource", -1);
    model.component("comp2").physics("cir2").feature("V1").setIndex("Connections", 3, 0, 0);
    model.component("comp2").physics("cir2").feature("V1").setIndex("Connections", 0, 1, 0);

    model.component("comp2").mesh("mesh2").autoMeshSize(6);
    model.component("comp2").mesh("mesh2").run();

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
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u7535\u52bf (ec2)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("solutionparams", "parent");
    model.result("pg3").feature("vol1").set("colortable", "Dipole");
    model.result("pg3").feature("vol1").set("showsolutionparams", "on");
    model.result("pg3").feature("vol1").set("data", "parent");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u7535\u573a (ec2)");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsmaxmin", true);
    model.result("pg4").feature().create("mslc1", "Multislice");
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("solutionparams", "parent");
    model.result("pg4").feature("mslc1").set("expr", "ec2.normE");
    model.result("pg4").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("mslc1").set("xcoord", "ec2.CPx");
    model.result("pg4").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("mslc1").set("ycoord", "ec2.CPy");
    model.result("pg4").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("mslc1").set("zcoord", "ec2.CPz");
    model.result("pg4").feature("mslc1").set("colortable", "Prism");
    model.result("pg4").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg4").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg4").feature("mslc1").set("data", "parent");
    model.result("pg4").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg4").feature("strmsl1").set("expr", new String[]{"ec2.Ex", "ec2.Ey", "ec2.Ez"});
    model.result("pg4").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg4").feature("strmsl1").set("xcoord", "ec2.CPx");
    model.result("pg4").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg4").feature("strmsl1").set("ycoord", "ec2.CPy");
    model.result("pg4").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg4").feature("strmsl1").set("zcoord", "ec2.CPz");
    model.result("pg4").feature("strmsl1").set("titletype", "none");
    model.result("pg4").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg4").feature("strmsl1").set("udist", 0.02);
    model.result("pg4").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg4").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("inheritcolor", false);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg4").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg4").feature("strmsl1").set("data", "parent");
    model.result("pg4").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg4").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg4").feature("strmsl1").feature("col1").set("expr", "ec2.normE");
    model.result("pg4").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg4").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg4").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg4").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg4").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result("pg1").create("arws1", "ArrowSurface");
    model.result("pg1").feature("arws1").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").create("arws1", "ArrowSurface");
    model.result("pg3").feature("arws1").set("color", "black");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "cir.v_0", 0);
    model.result().numerical("gev1").setIndex("expr", "cir.v_1", 1);
    model.result().numerical("gev1").setIndex("expr", "cir.v_2", 2);
    model.result().numerical("gev1").setIndex("expr", "cir.v_3", 3);
    model.result().numerical("gev1").setIndex("expr", "comp2.cir2.v_0", 4);
    model.result().numerical("gev1").setIndex("expr", "comp2.cir2.v_1", 5);
    model.result().numerical("gev1").setIndex("expr", "comp2.cir2.v_2", 6);
    model.result().numerical("gev1").setIndex("expr", "comp2.cir2.v_3", 7);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "((cir.v_2-cir.v_1)/cir.R1.v)*R1", 0);
    model.result().numerical("gev2").setIndex("expr", "((comp2.cir2.v_2-comp2.cir2.v_1)/comp2.cir2.R1.v)*R1", 1);
    model.result().numerical("gev2").setIndex("expr", "L/(pi*r^2*sigma)", 2);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result("pg2").run();
    model.result().remove("pg2");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg1").run();

    model.title("\u7535\u963b FEM \u573a\u8def\u8026\u5408");

    model
         .description("\u8fd9\u4e2a\u4ecb\u7ecd\u6027\u6a21\u578b\u8bf4\u660e\u4e86\u5728\u7535\u8def\u6a21\u578b\u4e2d\u5b9e\u73b0\u6709\u9650\u5143\u4eff\u771f\u7684\u4e24\u79cd\u4e0d\u540c\u8026\u5408\u65b9\u6cd5\u3002\u5728\u672c\u4f8b\u4e2d\uff0c\u6211\u4eec\u4f7f\u7528\u6709\u9650\u5143\u5206\u6790\u6765\u8868\u793a\u7535\u8def\u4e2d\u7684\u5706\u67f1\u5f62\u7535\u963b\uff0c\u5e76\u901a\u8fc7\u4e24\u79cd\u4e0d\u540c\u7684\u8026\u5408\u65b9\u5f0f\u4e0e\u7535\u8def\u8fde\u63a5\uff1a\u4e00\u79cd\u662f\u4f7f\u7528\u4e24\u4e2a\u201c\u5916\u90e8 I \u7ec8\u7aef\u201d\u7279\u5f81\u8fdb\u884c\u8026\u5408\uff0c\u53e6\u4e00\u79cd\u662f\u901a\u8fc7\u201c\u5916\u90e8 I vs. U\u201d\u7279\u5f81\u8fdb\u884c\u8026\u5408\u3002\u6211\u4eec\u5c06\u91cd\u70b9\u4ecb\u7ecd\u8fd9\u4e24\u79cd\u65b9\u6cd5\u4e4b\u95f4\u7684\u5dee\u5f02\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("circuit_fem_resistor.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

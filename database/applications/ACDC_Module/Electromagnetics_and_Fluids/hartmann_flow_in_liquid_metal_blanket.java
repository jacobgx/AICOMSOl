/*
 * hartmann_flow_in_liquid_metal_blanket.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class hartmann_flow_in_liquid_metal_blanket {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Fluids");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mef", "ElectricInductionCurrents", "geom1");
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_magneticvectorpotential", "1");
    model.component("comp1").physics("mef").prop("ShapeProperty").set("order_electricpotential", "1");
    model.component("comp1").physics("mef").create("alcf1", "ElectromagneticModelFluid");
    model.component("comp1").physics("mef").feature("alcf1").selection().all();
    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransferInFluids", "geom1");

    model.component("comp1").multiphysics().create("mhd1", "Magnetohydrodynamics", 3);
    model.component("comp1").multiphysics("mhd1").set("EMForce_physics", "mef");
    model.component("comp1").multiphysics("mhd1").set("FluidFlow_physics", "spf");
    model.component("comp1").multiphysics("mhd1").selection().all();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/mef", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std1").feature("stat").setSolveFor("/multiphysics/mhd1", true);

    model.param().set("d", "1[cm]");
    model.param().descr("d", "\u5e73\u9762\u7684\u534a\u95f4\u8ddd");
    model.param().set("U0", "0.05[m/s]");
    model.param().descr("U0", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param().set("B0", "0.02[T]");
    model.param().descr("B0", "\u78c1\u901a\u5bc6\u5ea6");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"40*d", "10*d", "2*d"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "-d"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("Lithium, 200 \u00b0C");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4[MS/m]", "0", "0", "0", "4[MS/m]", "0", "0", "0", "4[MS/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"47[W/(m*K)]", "0", "0", "0", "47[W/(m*K)]", "0", "0", "0", "47[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", "6.12E-04[Pa*s]");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "510[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"200"});

    model.component("comp1").multiphysics().create("nitf1", "NonIsothermalFlow", 3);
    model.component("comp1").multiphysics("nitf1").set("SpecifyDensity", "CustomLinearizedDensity");
    model.component("comp1").multiphysics("nitf1").set("rhoref_mat", "from_mat");
    model.component("comp1").multiphysics("nitf1").set("alphap", "1e-3[1/K]");

    model.component("comp1").physics("mef").create("bmfd1", "BackgroundMagneticFluxDensity", 2);
    model.component("comp1").physics("mef").feature("bmfd1").set("Bb", new String[]{"0", "0", "B0"});
    model.component("comp1").physics("mef").feature("bmfd1").selection().all();
    model.component("comp1").physics("mef").feature("bmfd1").create("ein1", "ElectricInsulation", 2);
    model.component("comp1").physics("mef").feature("bmfd1").feature("ein1").selection().all();
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 2);
    model.component("comp1").physics("spf").feature("inl1").selection().set(1);
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "U0");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 2);
    model.component("comp1").physics("spf").feature("out1").selection().set(6);
    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 2);
    model.component("comp1").physics("ht").feature("temp1").selection().set(1, 2);
    model.component("comp1").physics("ht").feature("temp1").set("T0", 450);
    model.component("comp1").physics("ht").create("hs1", "HeatSource", 3);
    model.component("comp1").physics("ht").feature("hs1").selection().all();
    model.component("comp1").physics("ht").feature("hs1").set("Q0", "1e6*exp(-((y-0.1[m])/0.02[m])^2)");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("size").set("table", "cfd");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(1, 2, 4, 6);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemcount", 30);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 40);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6 (mef)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("mslc1", "Multislice");
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("solutionparams", "parent");
    model.result("pg1").feature("mslc1").set("expr", "mef.normB");
    model.result("pg1").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("mslc1").set("xcoord", "mef.CPx");
    model.result("pg1").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("mslc1").set("ycoord", "mef.CPy");
    model.result("pg1").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("mslc1").set("zcoord", "mef.CPz");
    model.result("pg1").feature("mslc1").set("colortable", "Prism");
    model.result("pg1").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg1").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg1").feature("mslc1").set("data", "parent");
    model.result("pg1").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg1").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg1").feature("strmsl1").set("xcoord", "mef.CPx");
    model.result("pg1").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg1").feature("strmsl1").set("ycoord", "mef.CPy");
    model.result("pg1").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg1").feature("strmsl1").set("zcoord", "mef.CPz");
    model.result("pg1").feature("strmsl1").set("titletype", "none");
    model.result("pg1").feature("strmsl1").set("posmethod", "uniform");
    model.result("pg1").feature("strmsl1").set("udist", 0.02);
    model.result("pg1").feature("strmsl1").set("maxlen", 0.4);
    model.result("pg1").feature("strmsl1").set("maxsteps", 5000);
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("inheritcolor", false);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg1").feature("strmsl1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg1").feature("strmsl1").set("data", "parent");
    model.result("pg1").feature("strmsl1").set("inheritplot", "mslc1");
    model.result("pg1").feature("strmsl1").feature().create("col1", "Color");
    model.result("pg1").feature("strmsl1").feature("col1").set("expr", "mef.normB");
    model.result("pg1").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg1").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg1").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg1").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u573a (mef)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("mslc1", "Multislice");
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("solutionparams", "parent");
    model.result("pg2").feature("mslc1").set("expr", "mef.normE");
    model.result("pg2").feature("mslc1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("mslc1").set("xcoord", "mef.CPx");
    model.result("pg2").feature("mslc1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("mslc1").set("ycoord", "mef.CPy");
    model.result("pg2").feature("mslc1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("mslc1").set("zcoord", "mef.CPz");
    model.result("pg2").feature("mslc1").set("colortable", "Prism");
    model.result("pg2").feature("mslc1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mslc1").set("colorcalibration", -0.8);
    model.result("pg2").feature("mslc1").set("showsolutionparams", "on");
    model.result("pg2").feature("mslc1").set("data", "parent");
    model.result("pg2").feature().create("strmsl1", "StreamlineMultislice");
    model.result("pg2").feature("strmsl1").set("showsolutionparams", "on");
    model.result("pg2").feature("strmsl1").set("solutionparams", "parent");
    model.result("pg2").feature("strmsl1").set("expr", new String[]{"mef.Ex", "mef.Ey", "mef.Ez"});
    model.result("pg2").feature("strmsl1").set("multiplanexmethod", "coord");
    model.result("pg2").feature("strmsl1").set("xcoord", "mef.CPx");
    model.result("pg2").feature("strmsl1").set("multiplaneymethod", "coord");
    model.result("pg2").feature("strmsl1").set("ycoord", "mef.CPy");
    model.result("pg2").feature("strmsl1").set("multiplanezmethod", "coord");
    model.result("pg2").feature("strmsl1").set("zcoord", "mef.CPz");
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
    model.result("pg2").feature("strmsl1").feature("col1").set("expr", "mef.normE");
    model.result("pg2").feature("strmsl1").feature("col1").set("colortable", "PrismDark");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("strmsl1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("strmsl1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg2").feature("strmsl1").feature().create("filt1", "Filter");
    model.result("pg2").feature("strmsl1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").label("\u901f\u5ea6 (spf)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("slc1", "Slice");
    model.result("pg3").feature("slc1").label("\u5207\u9762");
    model.result("pg3").feature("slc1").set("showsolutionparams", "on");
    model.result("pg3").feature("slc1").set("expr", "spf.U");
    model.result("pg3").feature("slc1").set("smooth", "internal");
    model.result("pg3").feature("slc1").set("showsolutionparams", "on");
    model.result("pg3").feature("slc1").set("data", "parent");
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").label("\u5916\u58c1");
    model.result().dataset("surf1").set("data", "dset1");
    model.result().dataset("surf1").selection().geom("geom1", 2);
    model.result().dataset("surf1").selection().set(2, 3, 4, 5);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").label("\u538b\u529b (spf)");
    model.result("pg4").set("data", "surf1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").label("\u8868\u9762");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("expr", "p");
    model.result("pg4").feature("surf1").set("colortable", "Dipole");
    model.result("pg4").feature("surf1").set("smooth", "internal");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").feature("surf1").feature().create("tran1", "Transparency");
    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").label("\u6e29\u5ea6 (ht)");
    model.result("pg5").feature().create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("solutionparams", "parent");
    model.result("pg5").feature("vol1").set("expr", "T");
    model.result("pg5").feature("vol1").set("colortable", "HeatCameraLight");
    model.result("pg5").feature("vol1").set("smooth", "internal");
    model.result("pg5").feature("vol1").set("showsolutionparams", "on");
    model.result("pg5").feature("vol1").set("data", "parent");
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u6e29\u5ea6\u548c\u6d41\u4f53\u6d41\u52a8 (nitf1)");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u58c1\u6e29");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "ht.Tvar");
    model.result("pg6").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("sel1", "Selection");
    model.result("pg6").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg6").feature("surf1").feature("sel1").selection().set(2, 3, 4, 5);
    model.result("pg6").feature().create("arwv1", "ArrowVolume");
    model.result("pg6").feature("arwv1").label("\u6d41\u4f53\u6d41\u52a8");
    model.result("pg6").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg6").feature("arwv1").set("solutionparams", "parent");
    model.result("pg6").feature("arwv1").set("expr", new String[]{"nitf1.ux", "nitf1.uy", "nitf1.uz"});
    model.result("pg6").feature("arwv1").set("xnumber", 30);
    model.result("pg6").feature("arwv1").set("ynumber", 30);
    model.result("pg6").feature("arwv1").set("znumber", 30);
    model.result("pg6").feature("arwv1").set("arrowtype", "cone");
    model.result("pg6").feature("arwv1").set("arrowlength", "logarithmic");
    model.result("pg6").feature("arwv1").set("showsolutionparams", "on");
    model.result("pg6").feature("arwv1").set("data", "parent");
    model.result("pg6").feature("arwv1").feature().create("col1", "Color");
    model.result("pg6").feature("arwv1").feature("col1").set("showcolordata", "off");
    model.result("pg6").feature("arwv1").feature("col1").set("expr", "spf.U");
    model.result("pg6").feature("arwv1").feature().create("filt1", "Filter");
    model.result("pg6").feature("arwv1").feature("filt1").set("expr", "spf.U>nitf1.Uave");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").label("\u6d41\u4f53\u901f\u5ea6\u548c\u78c1\u573a");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("slc1").feature("def1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("slc1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("slc1").feature("def1").set("scale", 0.5);
    model.result("pg3").run();
    model.result("pg3").create("arwv1", "ArrowVolume");
    model.result("pg3").feature("arwv1").set("xnumber", 5);
    model.result("pg3").feature("arwv1").set("ynumber", 5);
    model.result("pg3").feature("arwv1").set("znumber", 1);
    model.result("pg3").feature("arwv1").set("color", "gray");
    model.result().dataset("surf1").selection().set(2, 3, 5);
    model.result("pg3").run();
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "1");
    model.result("pg3").feature("surf1").set("coloring", "uniform");
    model.result("pg3").feature("surf1").set("color", "gray");
    model.result("pg3").feature("surf1").set("data", "surf1");
    model.result("pg3").run();
    model.result().dataset().create("cpl1", "CutPlane");
    model.result().dataset("cpl1").set("quickx", "35*d");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u6d41\u5bc6\u5ea6\u548c\u8def\u5f84");
    model.result("pg7").set("data", "cpl1");
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("expr", "mef.Jy");
    model.result("pg7").run();
    model.result("pg7").create("arws1", "ArrowSurface");
    model.result("pg7").feature("arws1").set("planecoordsys", "cartesian");
    model.result("pg7").feature("arws1").set("expr", new String[]{"mef.Bx", "mef.By", "mef.Bz"});
    model.result("pg7").feature("arws1").set("descr", "\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg7").feature("arws1").setIndex("expr", "mef.Jx", 0);
    model.result("pg7").feature("arws1").setIndex("expr", "mef.Jy", 1);
    model.result("pg7").feature("arws1").setIndex("expr", "mef.Jz", 2);
    model.result("pg7").feature("arws1").set("color", "black");
    model.result("pg7").feature("arws1").set("scaleactive", true);
    model.result("pg7").feature("arws1").set("scale", 5.0E-6);
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("colortable", "Prism");
    model.result("pg5").run();
    model.result("pg5").create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("expr", "1");
    model.result("pg5").feature("surf1").set("data", "surf1");
    model.result("pg5").feature("surf1").set("coloring", "uniform");
    model.result("pg5").feature("surf1").set("color", "gray");
    model.result("pg5").run();
    model.result("pg5").feature("vol1").active(false);
    model.result("pg5").run();
    model.result("pg5").create("mslc1", "Multislice");
    model.result("pg5").feature("mslc1").set("expr", "T");
    model.result("pg5").feature("mslc1").set("xnumber", "5");
    model.result("pg5").feature("mslc1").set("znumber", "0");
    model.result("pg5").feature("mslc1").set("colortable", "ThermalDark");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u6db2\u6001\u91d1\u5c5e\u5305\u5c42\u4e2d\u8026\u5408\u4f20\u70ed\u7684\u54c8\u7279\u66fc\u6d41");

    model
         .description("\u5728\u6838\u805a\u53d8\u53cd\u5e94\u5806\u4e2d\uff0c\u6db2\u6001\u91d1\u5c5e\u4ee5\u4e00\u79cd\u79f0\u4e3a\u78c1\u6d41\u4f53\u52a8\u529b\u5b66 (MHD) \u7684\u6d41\u6001\u5f62\u5f0f\u4e0e\u80cc\u666f\u78c1\u573a\u76f8\u4e92\u4f5c\u7528\u3002\u672c\u6a21\u578b\u6f14\u793a\u6db2\u6001\u91d1\u5c5e\u5305\u5c42\u7ba1\u9053\u4e2d\u7684 MHD \u6d41\u52a8\uff0c\u5176\u4e2d\u7535\u78c1\u573a\u548c\u6d41\u4f53\u6d41\u52a8\u901a\u8fc7\u6d1b\u4f26\u5179\u529b\u548c\u7535\u52a8\u52bf\u8026\u5408\uff0c\u540e\u8005\u8fd8\u4e0e\u4f20\u70ed\u8026\u5408\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hartmann_flow_in_liquid_metal_blanket.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

/*
 * planoconvex_lens_orientation.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:19 by COMSOL 6.3.0.290. */
public class planoconvex_lens_orientation {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Lenses_Cameras_and_Telescopes");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("gop", "GeometricalOptics", "geom1");

    model.study().create("std1");
    model.study("std1").create("rtrac", "RayTracing");
    model.study("std1").feature("rtrac").setSolveFor("/physics/gop", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.geom()
         .load(new String[]{"part1"}, "Ray_Optics_Module\\3D\\Spherical_Lenses\\spherical_plano_convex_lens_3d.mph", new String[]{"part2"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature().duplicate("pi2", "pi1");
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepobj", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetopnt", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeeppnt", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowpnt", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetoedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowedg", new String[]{});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selcontributetobnd", new String[]{"none", "none", "none", "none"});
    model.component("comp1").geom("geom1").feature("pi1")
         .set("selkeepbnd", new String[]{"off", "off", "off", "off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowbnd", new String[]{"on", "on", "on", "on"});
    model.component("comp1").geom("geom1").feature("pi1").set("selcontributetodom", new String[]{"none"});
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepdom", new String[]{"off"});
    model.component("comp1").geom("geom1").feature("pi1").set("selshowdom", new String[]{"on"});
    model.component("comp1").geom("geom1").feature("pi2").setEntry("inputexpr", "niz", -1);
    model.component("comp1").geom("geom1").feature("pi2").set("displ", new String[]{"0", "100[mm]", "7.5[mm]"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1.5"});

    model.component("comp1").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 0, 0);
    model.component("comp1").physics("gop").prop("Results").setIndex("Results", "PlotSpotDiagramAndGeometricMTF", 0);
    model.component("comp1").physics("gop").feature("matd1").set("ReleaseReflectedRays", "Never");
    model.component("comp1").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp1").physics("gop").feature("relg1").set("GridType", "Hexapolar");
    model.component("comp1").physics("gop").feature("relg1").set("qcc", new String[]{"0", "0", "-10[mm]"});
    model.component("comp1").physics("gop").feature("relg1").set("rcc", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature("relg1").set("Rc", "20[mm]");
    model.component("comp1").physics("gop").feature("relg1").setIndex("Ncr", 25, 0);
    model.component("comp1").physics("gop").feature("relg1").set("L0", new int[]{0, 0, 1});
    model.component("comp1").physics("gop").feature().duplicate("relg2", "relg1");
    model.component("comp1").physics("gop").feature("relg2").set("qcc", new String[]{"0", "100[mm]", "-10[mm]"});

    model.component("comp1").mesh("mesh1").autoMeshSize(7);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("rtrac").set("tlist", "range(0,0.01,0.6)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol1");
    model.result().dataset("ray1").set("posdof", new String[]{"comp1.qx", "comp1.qy", "comp1.qz"});
    model.result().dataset("ray1").set("geom", "geom1");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "ray1");
    model.result("pg1").setIndex("looplevel", 61, 0);
    model.result("pg1").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg1").create("rtrj1", "RayTrajectories");
    model.result("pg1").feature("rtrj1").set("linetype", "line");
    model.result("pg1").feature("rtrj1").create("col1", "Color");
    model.result("pg1").feature("rtrj1").feature("col1").set("expr", "t");
    model.result("pg1").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("Spot Diagram");
    model.result("pg2").create("spot1", "SpotDiagram");
    model.result().dataset().create("ip1", "IntersectionPoint3D");
    model.result().dataset("ip1").set("data", "ray1");
    model.result("pg2").feature("spot1").set("data", "ip1");
    model.result("pg2").run();
    model.result("pg2").feature("spot1").runCommand("recomputeFocalPlaneDataset");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").label("Geometric MTF");
    model.result("pg3").set("data", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "Frequency (1/mm)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "MTF");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "Geometric Modulation Transfer Function (MTF)");
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("LSF Data (relg1)");
    model.result().evaluationGroup("eg1").set("data", "ip1");
    model.result().evaluationGroup("eg1").set("transpose", true);
    model.result().evaluationGroup("eg1").set("concatenation", "vertical");
    model.result().evaluationGroup("eg1").set("tablebuffersize", 100000);
    model.result().evaluationGroup("eg1").create("ray1", "Ray");
    model.result().evaluationGroup("eg1").feature("ray1").set("data", "ip1");
    model.result().evaluationGroup("eg1").feature("ray1").set("expr", "if(gop.prf==1, ip1y, NaN)");
    model.result().evaluationGroup("eg1").feature("ray1").set("unit", "mm");
    model.result().evaluationGroup("eg1").create("ray2", "Ray");
    model.result().evaluationGroup("eg1").feature("ray2").set("data", "ip1");
    model.result().evaluationGroup("eg1").feature("ray2").set("expr", "if(gop.prf==1, ip1x, NaN)");
    model.result().evaluationGroup("eg1").feature("ray2").set("unit", "mm");
    model.result().evaluationGroup("eg1").run();
    model.result().dataset().create("kde1", "KernelDensityEstimation");
    model.result().dataset("kde1").label("LSFx (eg1)");
    model.result().dataset("kde1").set("kerneltype", "parabolic");
    model.result().dataset("kde1").set("source", "evaluationgroup");
    model.result().dataset("kde1").set("evaluationgroup", "eg1");
    model.result().dataset("kde1").set("xaxisdata", "1");
    model.result().dataset("kde1").set("xvar", "eg1_x");
    model.result().dataset("kde1").set("outvar", "eg1_pdf_x");
    model.result().dataset().create("kde2", "KernelDensityEstimation");
    model.result().dataset("kde2").label("LSFy (eg1)");
    model.result().dataset("kde2").set("kerneltype", "parabolic");
    model.result().dataset("kde2").set("source", "evaluationgroup");
    model.result().dataset("kde2").set("evaluationgroup", "eg1");
    model.result().dataset("kde2").set("xaxisdata", "2");
    model.result().dataset("kde2").set("xvar", "eg1_x");
    model.result().dataset("kde2").set("outvar", "eg1_pdf_y");
    model.result().dataset().create("sfft1", "SpatialFFT");
    model.result().dataset("sfft1").label("MTFx (eg1)");
    model.result().dataset("sfft1").set("data", "kde1");
    model.result().dataset("sfft1").set("gridres", "manual");
    model.result().dataset("sfft1").set("sampresx", 32);
    model.result().dataset("sfft1").set("layout", "padding");
    model.result().dataset("sfft1").set("padx", 992);
    model.result().dataset("sfft1").set("maskdc", false);
    model.result().dataset("sfft1").set("normalizebydc", true);
    model.result().dataset("sfft1").set("fxvar", "eg1_fx");
    model.result().dataset().create("sfft2", "SpatialFFT");
    model.result().dataset("sfft2").label("MTFy (eg1)");
    model.result().dataset("sfft2").set("data", "kde2");
    model.result().dataset("sfft2").set("gridres", "manual");
    model.result().dataset("sfft2").set("sampresx", 32);
    model.result().dataset("sfft2").set("layout", "padding");
    model.result().dataset("sfft2").set("padx", 992);
    model.result().dataset("sfft2").set("maskdc", false);
    model.result().dataset("sfft2").set("normalizebydc", true);
    model.result().dataset("sfft2").set("fxvar", "eg1_fx");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").set("data", "sfft1");
    model.result("pg3").feature("lngr1").set("expr", "abs(fft(eg1_pdf_x))");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "eg1_fx");
    model.result("pg3").feature("lngr1").set("legendmethod", "manual");
    model.result("pg3").feature("lngr1").setIndex("legends", "MTFx (relg1)", 0);
    model.result("pg3").feature("lngr1").label("MTFx (relg1)");
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").create("lngr2", "LineGraph");
    model.result("pg3").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr2").set("linewidth", "preference");
    model.result("pg3").feature("lngr2").set("data", "sfft2");
    model.result("pg3").feature("lngr2").set("expr", "abs(fft(eg1_pdf_y))");
    model.result("pg3").feature("lngr2").set("xdata", "expr");
    model.result("pg3").feature("lngr2").set("xdataexpr", "eg1_fx");
    model.result("pg3").feature("lngr2").set("legendmethod", "manual");
    model.result("pg3").feature("lngr2").setIndex("legends", "MTFy (relg1)", 0);
    model.result("pg3").feature("lngr2").label("MTFy (relg1)");
    model.result("pg3").feature("lngr2").set("legend", true);
    model.result().evaluationGroup().create("eg2", "EvaluationGroup");
    model.result().evaluationGroup("eg2").label("LSF Data (relg2)");
    model.result().evaluationGroup("eg2").set("data", "ip1");
    model.result().evaluationGroup("eg2").set("transpose", true);
    model.result().evaluationGroup("eg2").set("concatenation", "vertical");
    model.result().evaluationGroup("eg2").set("tablebuffersize", 100000);
    model.result().evaluationGroup("eg2").create("ray1", "Ray");
    model.result().evaluationGroup("eg2").feature("ray1").set("data", "ip1");
    model.result().evaluationGroup("eg2").feature("ray1").set("expr", "if(gop.prf==2, ip1y, NaN)");
    model.result().evaluationGroup("eg2").feature("ray1").set("unit", "mm");
    model.result().evaluationGroup("eg2").create("ray2", "Ray");
    model.result().evaluationGroup("eg2").feature("ray2").set("data", "ip1");
    model.result().evaluationGroup("eg2").feature("ray2").set("expr", "if(gop.prf==2, ip1x, NaN)");
    model.result().evaluationGroup("eg2").feature("ray2").set("unit", "mm");
    model.result().evaluationGroup("eg2").run();
    model.result().dataset().create("kde3", "KernelDensityEstimation");
    model.result().dataset("kde3").label("LSFx (eg2)");
    model.result().dataset("kde3").set("kerneltype", "parabolic");
    model.result().dataset("kde3").set("source", "evaluationgroup");
    model.result().dataset("kde3").set("evaluationgroup", "eg2");
    model.result().dataset("kde3").set("xaxisdata", "1");
    model.result().dataset("kde3").set("xvar", "eg2_x");
    model.result().dataset("kde3").set("outvar", "eg2_pdf_x");
    model.result().dataset().create("kde4", "KernelDensityEstimation");
    model.result().dataset("kde4").label("LSFy (eg2)");
    model.result().dataset("kde4").set("kerneltype", "parabolic");
    model.result().dataset("kde4").set("source", "evaluationgroup");
    model.result().dataset("kde4").set("evaluationgroup", "eg2");
    model.result().dataset("kde4").set("xaxisdata", "2");
    model.result().dataset("kde4").set("xvar", "eg2_x");
    model.result().dataset("kde4").set("outvar", "eg2_pdf_y");
    model.result().dataset().create("sfft3", "SpatialFFT");
    model.result().dataset("sfft3").label("MTFx (eg2)");
    model.result().dataset("sfft3").set("data", "kde3");
    model.result().dataset("sfft3").set("gridres", "manual");
    model.result().dataset("sfft3").set("sampresx", 32);
    model.result().dataset("sfft3").set("layout", "padding");
    model.result().dataset("sfft3").set("padx", 992);
    model.result().dataset("sfft3").set("maskdc", false);
    model.result().dataset("sfft3").set("normalizebydc", true);
    model.result().dataset("sfft3").set("fxvar", "eg2_fx");
    model.result().dataset().create("sfft4", "SpatialFFT");
    model.result().dataset("sfft4").label("MTFy (eg2)");
    model.result().dataset("sfft4").set("data", "kde4");
    model.result().dataset("sfft4").set("gridres", "manual");
    model.result().dataset("sfft4").set("sampresx", 32);
    model.result().dataset("sfft4").set("layout", "padding");
    model.result().dataset("sfft4").set("padx", 992);
    model.result().dataset("sfft4").set("maskdc", false);
    model.result().dataset("sfft4").set("normalizebydc", true);
    model.result().dataset("sfft4").set("fxvar", "eg2_fx");
    model.result("pg3").create("lngr3", "LineGraph");
    model.result("pg3").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr3").set("linewidth", "preference");
    model.result("pg3").feature("lngr3").set("data", "sfft3");
    model.result("pg3").feature("lngr3").set("expr", "abs(fft(eg2_pdf_x))");
    model.result("pg3").feature("lngr3").set("xdata", "expr");
    model.result("pg3").feature("lngr3").set("xdataexpr", "eg2_fx");
    model.result("pg3").feature("lngr3").set("legendmethod", "manual");
    model.result("pg3").feature("lngr3").setIndex("legends", "MTFx (relg2)", 0);
    model.result("pg3").feature("lngr3").label("MTFx (relg2)");
    model.result("pg3").feature("lngr3").set("legend", true);
    model.result("pg3").create("lngr4", "LineGraph");
    model.result("pg3").feature("lngr4").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr4").set("linewidth", "preference");
    model.result("pg3").feature("lngr4").set("data", "sfft4");
    model.result("pg3").feature("lngr4").set("expr", "abs(fft(eg2_pdf_y))");
    model.result("pg3").feature("lngr4").set("xdata", "expr");
    model.result("pg3").feature("lngr4").set("xdataexpr", "eg2_fx");
    model.result("pg3").feature("lngr4").set("legendmethod", "manual");
    model.result("pg3").feature("lngr4").setIndex("legends", "MTFy (relg2)", 0);
    model.result("pg3").feature("lngr4").label("MTFy (relg2)");
    model.result("pg3").feature("lngr4").set("legend", true);
    model.result("pg1").run();
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").create("mtrl1", "MaterialAppearance");
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().dataset("ip1").setIndex("genpnpoint", 0, 0);
    model.result().dataset("ip1").setIndex("genpnpoint", 0, 1);
    model.result().dataset("ip1").set("genpnpoint", new String[]{"0", "0", "150[mm]"});
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().evaluationGroup("eg1").run();
    model.result().evaluationGroup("eg2").run();
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("xmin", 0);
    model.result("pg3").set("xmax", 10);
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("linestyle", "dashdot");
    model.result("pg3").run();
    model.result("pg3").feature("lngr4").set("linestyle", "dashdot");
    model.result("pg3").run();

    model.title("\u5e73\u51f8\u900f\u955c\u5b9a\u5411");

    model
         .description("\u5728\u5b9e\u9a8c\u5ba4\u4f7f\u7528\u6fc0\u5149\u65f6\uff0c\u5e38\u5e38\u9700\u8981\u5bf9\u5149\u675f\u8fdb\u884c\u51c6\u76f4\u6216\u805a\u7126\uff0c\u5355\u900f\u955c\u56e0\u5176\u7ed3\u6784\u7b80\u5355\u800c\u88ab\u9891\u7e41\u91c7\u7528\u3002\u5982\u679c\u4f7f\u7528\u5e73\u51f8\u900f\u955c\u6765\u6267\u884c\u6b64\u4efb\u52a1\uff0c\u6709\u4e00\u79cd\u6b63\u786e\u7684\u900f\u955c\u5b9a\u5411\u65b9\u5f0f\uff0c\u53ef\u4ee5\u5c06\u50cf\u5dee\u6700\u5c0f\u5316\u3002\u4e00\u4e2a\u5b9e\u7528\u7684\u7ecf\u9a8c\u6cd5\u5219\u662f\uff0c\u901a\u8fc7\u5c06\u900f\u955c\u7684\u66f2\u9762\u671d\u5411\u51c6\u76f4\u5149\u675f\uff0c\u53ef\u4ee5\u5c3d\u91cf\u51cf\u5c11\u6bcf\u4e2a\u8868\u9762\u7684\u5149\u7ebf\u6298\u5c04\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.result("pg3").feature("lngr2").set("constisupdated", false);
    model.result("pg3").feature("lngr2").set("evalmethodactive", false);
    model.result("pg3").feature("lngr2").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr2").set("showlayerlocation", false);
    model.result("pg3").feature("lngr2").set("showlocationinput", false);
    model.result("pg3").feature("lngr2").set("olddataset", "sfft2");
    model.result().dataset("sfft2").set("showresx", true);
    model.result().dataset("sfft2").set("showpadx", true);
    model.result().dataset("sfft2").set("showvarx", true);
    model.result().dataset("sfft2").set("showresy", false);
    model.result().dataset("sfft2").set("showpady", false);
    model.result().dataset("sfft2").set("showvary", false);
    model.result().dataset("sfft2").set("showresz", false);
    model.result().dataset("sfft2").set("showpadz", false);
    model.result().dataset("sfft2").set("showvarz", false);
    model.result().dataset("kde2").set("xaxisdata", "2");
    model.result().dataset("kde2").set("yaxisdata", "none");
    model.result().dataset("kde2").set("zaxisdata", "none");
    model.result("pg3").feature("lngr2").set("constisupdated", false);
    model.result("pg3").feature("lngr2").set("evalmethodactive", false);
    model.result("pg3").feature("lngr2").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr2").set("showlayerlocation", false);
    model.result("pg3").feature("lngr2").set("showlocationinput", false);
    model.result("pg3").feature("lngr2").set("olddataset", "sfft2");
    model.result().dataset("sfft2").set("showresx", true);
    model.result().dataset("sfft2").set("showpadx", true);
    model.result().dataset("sfft2").set("showvarx", true);
    model.result().dataset("sfft2").set("showresy", false);
    model.result().dataset("sfft2").set("showpady", false);
    model.result().dataset("sfft2").set("showvary", false);
    model.result().dataset("sfft2").set("showresz", false);
    model.result().dataset("sfft2").set("showpadz", false);
    model.result().dataset("sfft2").set("showvarz", false);
    model.result("pg3").feature("lngr1").set("constisupdated", false);
    model.result("pg3").feature("lngr1").set("evalmethodactive", false);
    model.result("pg3").feature("lngr1").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr1").set("showlayerlocation", false);
    model.result("pg3").feature("lngr1").set("showlocationinput", false);
    model.result("pg3").feature("lngr1").set("olddataset", "sfft1");
    model.result().dataset("sfft1").set("showresx", true);
    model.result().dataset("sfft1").set("showpadx", true);
    model.result().dataset("sfft1").set("showvarx", true);
    model.result().dataset("sfft1").set("showresy", false);
    model.result().dataset("sfft1").set("showpady", false);
    model.result().dataset("sfft1").set("showvary", false);
    model.result().dataset("sfft1").set("showresz", false);
    model.result().dataset("sfft1").set("showpadz", false);
    model.result().dataset("sfft1").set("showvarz", false);
    model.result().dataset("kde1").set("xaxisdata", "1");
    model.result().dataset("kde1").set("yaxisdata", "none");
    model.result().dataset("kde1").set("zaxisdata", "none");
    model.result("pg3").feature("lngr1").set("constisupdated", false);
    model.result("pg3").feature("lngr1").set("evalmethodactive", false);
    model.result("pg3").feature("lngr1").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr1").set("showlayerlocation", false);
    model.result("pg3").feature("lngr1").set("showlocationinput", false);
    model.result("pg3").feature("lngr1").set("olddataset", "sfft1");
    model.result().dataset("sfft1").set("showresx", true);
    model.result().dataset("sfft1").set("showpadx", true);
    model.result().dataset("sfft1").set("showvarx", true);
    model.result().dataset("sfft1").set("showresy", false);
    model.result().dataset("sfft1").set("showpady", false);
    model.result().dataset("sfft1").set("showvary", false);
    model.result().dataset("sfft1").set("showresz", false);
    model.result().dataset("sfft1").set("showpadz", false);
    model.result().dataset("sfft1").set("showvarz", false);
    model.result("pg3").feature("lngr4").set("constisupdated", false);
    model.result("pg3").feature("lngr4").set("evalmethodactive", false);
    model.result("pg3").feature("lngr4").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr4").set("showlayerlocation", false);
    model.result("pg3").feature("lngr4").set("showlocationinput", false);
    model.result("pg3").feature("lngr4").set("olddataset", "sfft4");
    model.result().dataset("sfft4").set("showresx", true);
    model.result().dataset("sfft4").set("showpadx", true);
    model.result().dataset("sfft4").set("showvarx", true);
    model.result().dataset("sfft4").set("showresy", false);
    model.result().dataset("sfft4").set("showpady", false);
    model.result().dataset("sfft4").set("showvary", false);
    model.result().dataset("sfft4").set("showresz", false);
    model.result().dataset("sfft4").set("showpadz", false);
    model.result().dataset("sfft4").set("showvarz", false);
    model.result().dataset("kde4").set("xaxisdata", "2");
    model.result().dataset("kde4").set("yaxisdata", "none");
    model.result().dataset("kde4").set("zaxisdata", "none");
    model.result("pg3").feature("lngr4").set("constisupdated", false);
    model.result("pg3").feature("lngr4").set("evalmethodactive", false);
    model.result("pg3").feature("lngr4").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr4").set("showlayerlocation", false);
    model.result("pg3").feature("lngr4").set("showlocationinput", false);
    model.result("pg3").feature("lngr4").set("olddataset", "sfft4");
    model.result().dataset("sfft4").set("showresx", true);
    model.result().dataset("sfft4").set("showpadx", true);
    model.result().dataset("sfft4").set("showvarx", true);
    model.result().dataset("sfft4").set("showresy", false);
    model.result().dataset("sfft4").set("showpady", false);
    model.result().dataset("sfft4").set("showvary", false);
    model.result().dataset("sfft4").set("showresz", false);
    model.result().dataset("sfft4").set("showpadz", false);
    model.result().dataset("sfft4").set("showvarz", false);
    model.result("pg3").feature("lngr3").set("constisupdated", false);
    model.result("pg3").feature("lngr3").set("evalmethodactive", false);
    model.result("pg3").feature("lngr3").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr3").set("showlayerlocation", false);
    model.result("pg3").feature("lngr3").set("showlocationinput", false);
    model.result("pg3").feature("lngr3").set("olddataset", "sfft3");
    model.result().dataset("sfft3").set("showresx", true);
    model.result().dataset("sfft3").set("showpadx", true);
    model.result().dataset("sfft3").set("showvarx", true);
    model.result().dataset("sfft3").set("showresy", false);
    model.result().dataset("sfft3").set("showpady", false);
    model.result().dataset("sfft3").set("showvary", false);
    model.result().dataset("sfft3").set("showresz", false);
    model.result().dataset("sfft3").set("showpadz", false);
    model.result().dataset("sfft3").set("showvarz", false);
    model.result().dataset("kde3").set("xaxisdata", "1");
    model.result().dataset("kde3").set("yaxisdata", "none");
    model.result().dataset("kde3").set("zaxisdata", "none");
    model.result("pg3").feature("lngr3").set("constisupdated", false);
    model.result("pg3").feature("lngr3").set("evalmethodactive", false);
    model.result("pg3").feature("lngr3").set("xdataevalmethodactive", false);
    model.result("pg3").feature("lngr3").set("showlayerlocation", false);
    model.result("pg3").feature("lngr3").set("showlocationinput", false);
    model.result("pg3").feature("lngr3").set("olddataset", "sfft3");
    model.result().dataset("sfft3").set("showresx", true);
    model.result().dataset("sfft3").set("showpadx", true);
    model.result().dataset("sfft3").set("showvarx", true);
    model.result().dataset("sfft3").set("showresy", false);
    model.result().dataset("sfft3").set("showpady", false);
    model.result().dataset("sfft3").set("showvary", false);
    model.result().dataset("sfft3").set("showresz", false);
    model.result().dataset("sfft3").set("showpadz", false);
    model.result().dataset("sfft3").set("showvarz", false);

    model.label("planoconvex_lens_orientation.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

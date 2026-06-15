/*
 * self_focusing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:40 by COMSOL 6.3.0.290. */
public class self_focusing {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Nonlinear_Optics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("ewbe", "ElectromagneticWavesBeamEnvelopes", "geom1");

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
    model.study("std1").feature("wave").setSolveFor("/physics/ewbe", true);

    model.param().set("lda0", "1.064[um]");
    model.param().descr("lda0", "\u6ce2\u957f");
    model.param().set("w0", "100*lda0");
    model.param().descr("w0", "\u6807\u79f0\u5149\u6591\u534a\u5f84");
    model.param().set("n0", "1.52");
    model.param().descr("n0", "BK-7 \u73bb\u7483\u7684\u6298\u5c04\u7387");
    model.param().set("x0", "pi*n0*w0^2/lda0");
    model.param().descr("x0", "\u745e\u5229\u8303\u56f4");
    model.param().set("k", "2*pi*n0/lda0");
    model.param().descr("k", "\u4f20\u64ad\u5e38\u6570");
    model.param().set("I0", "2.5[GW/cm^2]");
    model.param().descr("I0", "\u6807\u79f0\u5cf0\u503c\u5f3a\u5ea6");
    model.param().set("E0", "sqrt(2*I0/n0*sqrt(mu0_const/epsilon0_const))");
    model.param().descr("E0", "\u6807\u79f0\u5cf0\u503c\u7535\u573a");
    model.param().set("length", "4*x0");
    model.param().descr("length", "\u8ba1\u7b97\u57df\u7684\u957f\u5ea6");
    model.param().set("radius", "2.5*w0*sqrt(1+(length/(2*x0))^2)");
    model.param().descr("radius", "\u8ba1\u7b97\u57df\u7684\u534a\u5f84");
    model.param().set("gamma", "4e-16[cm^2/W]");
    model.param().descr("gamma", "\u975e\u7ebf\u6027\u6298\u5c04\u7387\u7cfb\u6570");
    model.param().set("delta_n", "gamma*I0");
    model.param().descr("delta_n", "\u6807\u79f0\u6298\u5c04\u7387\u53d8\u5316");
    model.param().set("P_cr", "(1.22*pi)^2*lda0^2/(8*pi*n0*gamma)");
    model.param().descr("P_cr", "\u4e34\u754c\u529f\u7387");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "radius");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "length");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-length/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"length", "radius", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "radius", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-length/2", "-radius", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "cyl1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").camera().set("viewscaletype", "automatic");
    model.component("comp1").view("view1").camera().set("autocontext", "anisotropic");
    model.component("comp1").view("view1").camera().set("autoupdate", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("BK-7 \u73bb\u7483");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"n0+gamma*ewbe.Poavx"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").set("opname", "intop_output_boundary");
    model.component("comp1").cpl("intop1").selection().geom("geom1", 2);
    model.component("comp1").cpl("intop1").selection().set(5);
    model.component("comp1").cpl().create("linproj1", "LinearProjection");
    model.component("comp1").cpl("linproj1").set("opname", "proj2D");
    model.component("comp1").cpl("linproj1").selection().set(1);
    model.component("comp1").cpl("linproj1").selection("srcvertex1").set(2);
    model.component("comp1").cpl("linproj1").selection("srcvertex2").set(5);
    model.component("comp1").cpl("linproj1").selection("srcvertex3").set(6);
    model.component("comp1").cpl("linproj1").selection("srcvertex4").set(1);
    model.component("comp1").cpl("linproj1").selection("dstvertex1").set(2);
    model.component("comp1").cpl("linproj1").selection("dstvertex2").set(5);
    model.component("comp1").cpl("linproj1").selection("dstvertex3").set(6);
    model.component("comp1").cpl().create("linproj2", "LinearProjection");
    model.component("comp1").cpl("linproj2").set("opname", "proj1D");
    model.component("comp1").cpl("linproj2").selection().geom("geom1", 2);
    model.component("comp1").cpl("linproj2").selection().set(4);
    model.component("comp1").cpl("linproj2").selection("srcvertex1").set(2);
    model.component("comp1").cpl("linproj2").selection("srcvertex2").set(5);
    model.component("comp1").cpl("linproj2").selection("srcvertex3").set(3);
    model.component("comp1").cpl("linproj2").selection("dstvertex1").set(2);
    model.component("comp1").cpl("linproj2").selection("dstvertex2").set(5);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("P", "intop_output_boundary(ewbe.nPoav)");
    model.component("comp1").variable("var1").descr("P", "\u8f93\u51fa\u529f\u7387");
    model.component("comp1").variable("var1").set("w_t", "sqrt(2*intop_output_boundary(ewbe.nPoav*(y^2+z^2))/P)");
    model.component("comp1").variable("var1")
         .descr("w_t", "\u8f93\u51fa\u8fb9\u754c\u4e0a\u7684\u5149\u6591\u534a\u5f84");
    model.component("comp1").variable("var1")
         .set("w_t_x", "sqrt(2*proj1D(proj2D(ewbe.Poavx*(y^2+z^2)))/proj1D(proj2D(ewbe.Poavx)))");

    model.component("comp1").physics("ewbe").prop("WaveVector").set("dirCount", "UniDirectionality");
    model.component("comp1").physics("ewbe").prop("WaveVector").set("k1", new String[]{"k", "0", "0"});
    model.component("comp1").physics("ewbe").create("mbc1", "MatchedBoundaryCondition", 2);
    model.component("comp1").physics("ewbe").feature("mbc1").selection().set(1);
    model.component("comp1").physics("ewbe").feature("mbc1").set("IncidentField", "GaussianBeam");
    model.component("comp1").physics("ewbe").feature("mbc1").set("w0", "w0");
    model.component("comp1").physics("ewbe").feature("mbc1").set("p0", "length/2");
    model.component("comp1").physics("ewbe").feature("mbc1").set("inputQuantity", "Power");
    model.component("comp1").physics("ewbe").feature("mbc1").set("inputPower", "pi*w0^2/2*I0");
    model.component("comp1").physics("ewbe").feature("mbc1").set("Eg0NN", new String[]{"0", "0", "1[V/m]"});
    model.component("comp1").physics("ewbe").feature("mbc1").create("rpnt1", "ReferencePoint", 0);
    model.component("comp1").physics("ewbe").feature("mbc1").feature("rpnt1").selection().set(2);
    model.component("comp1").physics("ewbe").create("mbc2", "MatchedBoundaryCondition", 2);
    model.component("comp1").physics("ewbe").feature("mbc2").selection().set(5);
    model.component("comp1").physics("ewbe").create("symp1", "SymmetryPlane", 2);
    model.component("comp1").physics("ewbe").feature("symp1").selection().set(4);
    model.component("comp1").physics("ewbe").create("symp2", "SymmetryPlane", 2);
    model.component("comp1").physics("ewbe").feature("symp2").selection().set(2);
    model.component("comp1").physics("ewbe").feature("symp2").set("Symmetry_type", "pec");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "x0/2");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "w0/2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmin", "w0/4");
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("wave").set("plist", "lda0");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "lda0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "I0", 0);
    model.study("std1").feature("param").setIndex("plistarr", "1e7, 2e13, 5e13, 8e13, 11e13, 14e13", 0);
    model.study("std1").feature("param").set("paramselect", false);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("st1").set("splitcomplex", true);

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("slc1", "Slice");
    model.result("pg1").feature("slc1").set("quickplane", "xy");
    model.result("pg1").feature("slc1").set("quickzmethod", "coord");
    model.result("pg1").feature("slc1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").feature("def1").set("expr", new String[]{"", "", "ewbe.normE"});
    model.result("pg1").run();
    model.result("pg1").set("edges", false);
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 2, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 3, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 5, 1);
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 6, 1);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("slc1").set("expr", "ewbe.normE/E0");
    model.result("pg1").run();
    model.result("pg1").feature("slc1").feature("def1").set("expr", new String[]{"", "", "ewbe.normE/E0"});
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("solnumtype", "level2");
    model.result().export("anim1").set("frametime", 1);
    model.result().export("anim1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("quickplane", "xy");
    model.result("pg2").feature("slc1").set("quickzmethod", "coord");
    model.result("pg2").feature("slc1").set("expr", "ewbe.nxx-n0");
    model.result("pg2").feature("slc1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("slc1").feature("def1").set("expr", new String[]{"", "", "ewbe.nxx-n0"});
    model.result("pg2").run();
    model.result("pg2").set("edges", false);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u975e\u7ebf\u6027\u6298\u5c04\u7387\uff1aewbe.nxx-n0");
    model.result("pg2").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "w_t", 0);
    model.result().numerical("gev1").set("data", "dset2");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "none");
    model.result("pg3").create("tblp1", "Table");
    model.result("pg3").feature("tblp1").set("source", "table");
    model.result("pg3").feature("tblp1").set("table", "tbl1");
    model.result("pg3").feature("tblp1").set("linewidth", "preference");
    model.result("pg3").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("xaxisdata", 1);
    model.result("pg3").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg3").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg3").run();
    model.result("pg3").set("axislimits", true);
    model.result("pg3").set("ymin", "1.5e-4");
    model.result("pg3").set("ymax", "2.5e-4");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevelinput", "manual", 1);
    model.result("pg4").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(5);
    model.result("pg4").feature("lngr1").set("expr", "w_t_x");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "General spot radius", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "w0*sqrt(1+(x/x0)^2)");
    model.result("pg4").feature("lngr2").set("linestyle", "dashed");
    model.result("pg4").feature("lngr2").setIndex("legends", "Paraxial spot radius", 0);
    model.result("pg4").run();
    model.result().numerical().create("gev2", "EvalGlobal");
    model.result().numerical("gev2").setIndex("expr", "w0*sqrt(1+(length/2/x0)^2)", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u5168\u5c40\u8ba1\u7b97 2");
    model.result().numerical("gev2").set("table", "tbl2");
    model.result().numerical("gev2").setResult();
    model.result().numerical().create("gev3", "EvalGlobal");
    model.result().numerical("gev3").setIndex("expr", "4*P/P_cr", 0);
    model.result().numerical("gev3").set("data", "dset2");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 3");
    model.result().numerical("gev3").set("table", "tbl3");
    model.result().numerical("gev3").setResult();
    model.result("pg2").run();

    model.title("\u81ea\u805a\u7126");

    model
         .description("\u9ad8\u65af\u5149\u675f\u6295\u5c04\u5230 BK-7 \u5149\u5b66\u73bb\u7483\u4e0a\uff0c\u6750\u6599\u7684\u6298\u5c04\u7387\u968f\u5f3a\u5ea6\u53d8\u5316\uff0c\u5728\u5149\u675f\u6b63\u4e2d\u5fc3\uff0c\u6298\u5c04\u7387\u6700\u5927\u3002\u7531\u6b64\u4ea7\u751f\u6298\u5c04\u7387\u5256\u9762\uff0c\u62b5\u6d88\u4e86\u884d\u5c04\u6548\u5e94\uff0c\u6700\u7ec8\u4ea7\u751f\u805a\u7126\u3002\u8fd9\u5c31\u662f\u81ea\u805a\u7126\u3002\u8fd9\u662f\u4e00\u79cd\u9ad8\u80fd\u6fc0\u5149\u7cfb\u7edf\u4e2d\u7684\u91cd\u8981\u8bbe\u8ba1\u3002\u672c\u4f8b\u6f14\u793a\u4e09\u7ef4\u975e\u7ebf\u6027\u6ce2\u7684\u4f20\u64ad\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("self_focusing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

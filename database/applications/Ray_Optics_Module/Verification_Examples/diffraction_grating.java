/*
 * diffraction_grating.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:26 by COMSOL 6.3.0.290. */
public class diffraction_grating {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Ray_Optics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").set("ftplistmethod", "manual");
    model.study("std1").feature("freq").set("solnum", "auto");
    model.study("std1").feature("freq").set("notsolnum", "auto");
    model.study("std1").feature("freq").set("outputmap", new String[]{});
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").set("ngenAUX", "1");
    model.study("std1").feature("freq").set("goalngenAUX", "1");
    model.study("std1").feature("freq").setSolveFor("/physics/ewfd", true);

    model.param().set("n_air", "1");
    model.param().descr("n_air", "\u6298\u5c04\u7387\uff0c\u7a7a\u6c14");
    model.param().set("n_sio2", "1.54874");
    model.param().descr("n_sio2", "\u6298\u5c04\u7387\uff0cSiO2");
    model.param().set("d", "340[nm]");
    model.param().descr("d", "\u5149\u6805\u5e38\u6570");
    model.param().set("lam0", "441[nm]");
    model.param().descr("lam0", "\u5165\u5c04\u5149\u7684\u771f\u7a7a\u6ce2\u957f");
    model.param().set("f0", "c_const/lam0");
    model.param().descr("f0", "\u5165\u5c04\u5149\u7684\u9891\u7387");
    model.param().set("alpha", "0.0[deg]");
    model.param().descr("alpha", "\u5165\u5c04\u89d2\uff08\u8f93\u5165\u7aef\u53e3\uff09");

    model.material().create("mat1", "Common", "");
    model.material("mat1").label("\u7a7a\u6c14");
    model.material("mat1").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"n_air"});
    model.material().create("mat2", "Common", "");
    model.material("mat2").label("SiO2");
    model.material("mat2").propertyGroup().create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.material("mat2").propertyGroup("RefractiveIndex").set("n", new String[]{"n_sio2"});

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"d", "6*d"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "-3*d"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"d", "3*d"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "-3*d"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new String[]{"d/2", "d/4"});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"d/4", "0"});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r2", "r3");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("matlnk1", "Link");
    model.component("comp1").material("matlnk1").selection().set(2);
    model.component("comp1").material().create("matlnk2", "Link");
    model.component("comp1").material("matlnk2").selection().set(1);
    model.component("comp1").material("matlnk2").set("link", "mat2");

    model.study("std1").feature("freq").set("plist", "f0");

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("port1", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port1").selection().set(5);
    model.component("comp1").physics("ewfd").feature("port1").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port1").set("alpha_inc", "alpha");
    model.component("comp1").physics("ewfd").feature("port1").set("DiffractionOrderSpecification", "AllAngles");
    model.component("comp1").physics("ewfd").create("port2", "Port", 1);
    model.component("comp1").physics("ewfd").feature("port2").selection().set(2);
    model.component("comp1").physics("ewfd").feature("port2").set("PortType", "Periodic");
    model.component("comp1").physics("ewfd").feature("port1").runCommand("addDiffractionOrders");
    model.component("comp1").physics("ewfd").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("ewfd").feature("pc1").selection().set(1, 3, 10, 11);
    model.component("comp1").physics("ewfd").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("ewfd").feature("pc1").set("Floquet_source", "FromPeriodicPort");

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "n_air", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "n_air", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "alpha", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,1,90)", 0);
    model.study("std1").feature("param").setIndex("punit", "deg", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{"", "", "", "", "", "", "", "", "", ""});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"ewfd.Rorder_0", "ewfd.Rorder_n1_op", "ewfd.Rorder_p1_op", "ewfd.Rtotal", "ewfd.Torder_0", "ewfd.Torder_n1_op", "ewfd.Torder_p1_op", "ewfd.Ttotal", "ewfd.RTtotal", "ewfd.Atotal"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u53cd\u5c04\u7387\uff0c\u9636\u6570 0", "\u53cd\u5c04\u7387\uff0c-1 \u9636\uff0c\u9762\u5916", "\u53cd\u5c04\u7387\uff0c1 \u9636\uff0c\u9762\u5916", "\u603b\u53cd\u5c04\u7387", "\u900f\u5c04\u7387\uff0c\u9636\u6570 0", "\u900f\u5c04\u7387\uff0c-1 \u9636\uff0c\u9762\u5916", "\u900f\u5c04\u7387\uff0c1 \u9636\uff0c\u9762\u5916", "\u603b\u900f\u5c04\u7387", "\u603b\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387", "\u5438\u6536\u7387"});
    model.result("pg2").label("\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (ewfd)");
    model.result("pg2").feature("glob1").set("titletype", "none");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u53cd\u5c04\u7387\u3001\u900f\u5c04\u7387\u548c\u5438\u6536\u7387 (1)");
    model.result("pg2").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg2").feature("glob1").set("xdataunit", "deg");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "all");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 46, 0);
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "ewfd.Rorder_0", 0);
    model.result().numerical("gev1").setIndex("descr", "R0", 0);
    model.result().numerical("gev1").setIndex("expr", "ewfd.Torder_0", 1);
    model.result().numerical("gev1").setIndex("descr", "T0", 1);
    model.result().numerical("gev1").setIndex("expr", "ewfd.Rorder_n1_op", 2);
    model.result().numerical("gev1").setIndex("descr", "R-1", 2);
    model.result().numerical("gev1").setIndex("expr", "ewfd.Torder_n1_op", 3);
    model.result().numerical("gev1").setIndex("descr", "T-1", 3);
    model.result().numerical("gev1").setIndex("expr", "ewfd.Rorder_p1_op", 4);
    model.result().numerical("gev1").setIndex("descr", "R1", 4);
    model.result().numerical("gev1").setIndex("expr", "ewfd.Torder_p1_op", 5);
    model.result().numerical("gev1").setIndex("descr", "T1", 5);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "middleleft");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("base", "center");
    model.component("comp2").geom("geom2").feature("r1").set("size", new String[]{"5[mm]", "1.35[mm]"});
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("r2").set("size", new String[]{"5[mm]", "1"});
    model.component("comp2").geom("geom2").feature("r2").set("base", "center");
    model.component("comp2").geom("geom2").feature("r2").set("size", new String[]{"5[mm]", "0.675[mm]"});
    model.component("comp2").geom("geom2").feature("r2").set("pos", new String[]{"0", "-0.675[mm]/2"});
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").func().create("int1", "Interpolation");
    model.component("comp2").func("int1").set("source", "resultTable");
    model.component("comp2").func("int1").setEntry("columnType", "col3", "value");
    model.component("comp2").func("int1").setEntry("columnType", "col4", "value");
    model.component("comp2").func("int1").setEntry("columnType", "col5", "value");
    model.component("comp2").func("int1").setEntry("columnType", "col6", "value");
    model.component("comp2").func("int1").setEntry("columnType", "col7", "value");
    model.component("comp2").func("int1").setIndex("argunit", "deg", 0);
    model.component("comp2").func("int1").setEntry("funcnames", "col2", "R0");
    model.component("comp2").func("int1").setIndex("fununit", "1", 0);
    model.component("comp2").func("int1").setEntry("funcnames", "col3", "T0");
    model.component("comp2").func("int1").setIndex("fununit", "1", 1);
    model.component("comp2").func("int1").setEntry("funcnames", "col4", "Rm1");
    model.component("comp2").func("int1").setIndex("fununit", "1", 2);
    model.component("comp2").func("int1").setEntry("funcnames", "col5", "Tm1");
    model.component("comp2").func("int1").setIndex("fununit", "1", 3);
    model.component("comp2").func("int1").setEntry("funcnames", "col6", "R1");
    model.component("comp2").func("int1").setIndex("fununit", "1", 4);
    model.component("comp2").func("int1").setEntry("funcnames", "col7", "T1");
    model.component("comp2").func("int1").setIndex("fununit", "1", 5);

    model.component("comp2").physics().create("gop", "GeometricalOptics", "geom2");

    model.study("std1").feature("freq").setSolveFor("/physics/gop", true);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().create("matlnk3", "Link");
    model.component("comp2").material("matlnk3").selection().set(2);
    model.component("comp2").material().create("matlnk4", "Link");
    model.component("comp2").material("matlnk4").selection().set(1);
    model.component("comp2").material("matlnk4").set("link", "mat2");

    model.component("comp2").physics("gop").prop("IntensityComputation")
         .setIndex("IntensityComputation", "ComputeIntensityAndPower", 0);
    model.component("comp2").physics("gop").prop("MaximumSecondary").setIndex("MaximumSecondary", 4505, 0);
    model.component("comp2").physics("gop").feature("op1").set("RayPropertySpecification", "SpecifyFrequency");
    model.component("comp2").physics("gop").feature("op1").set("nu", "f0");

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1").set("alpha_ro", "atan2(kx,-ky)");

    model.component("comp2").physics("gop").create("grat1", "Grating", 1);
    model.component("comp2").physics("gop").feature("grat1").selection().set(4);
    model.component("comp2").physics("gop").feature("grat1").set("dg", "d");
    model.component("comp2").physics("gop").feature("grat1").set("StoreTotalTransmittedPower", true);
    model.component("comp2").physics("gop").feature("grat1").set("StoreTotalReflectedPower", true);
    model.component("comp2").physics("gop").feature("grat1").feature("dfo1").set("Rf", "R0(alpha_ro)");
    model.component("comp2").physics("gop").feature("grat1").feature("dfo1").set("Tr", "T0(alpha_ro)");
    model.component("comp2").physics("gop").feature("grat1").create("dfo2", "DiffractionOrder", 1);
    model.component("comp2").physics("gop").feature("grat1").feature("dfo2").set("mg", -1);
    model.component("comp2").physics("gop").feature("grat1").feature("dfo2").set("Rf", "Rm1(alpha_ro)");
    model.component("comp2").physics("gop").feature("grat1").feature("dfo2").set("Tr", "T1(alpha_ro)");
    model.component("comp2").physics("gop").feature("grat1").create("dfo3", "DiffractionOrder", 1);
    model.component("comp2").physics("gop").feature("grat1").feature("dfo3").set("Rf", "R1(alpha_ro)");
    model.component("comp2").physics("gop").feature("grat1").feature("dfo3").set("Tr", "Tm1(alpha_ro)");
    model.component("comp2").physics("gop").create("relg1", "ReleaseGrid", -1);
    model.component("comp2").physics("gop").feature("relg1").setIndex("x0", "1e-6", 1);
    model.component("comp2").physics("gop").feature("relg1").set("RayDirectionVector", "Conical");
    model.component("comp2").physics("gop").feature("relg1").setIndex("Nw", 901, 0);
    model.component("comp2").physics("gop").feature("relg1").set("alphac", "pi/4");
    model.component("comp2").physics("gop").feature("relg1").set("cax", new double[]{1, -1.01, 0});
    model.component("comp2").physics("gop").feature("relg1").set("Psrc", "901[W/m]");
    model.component("comp2").physics("gop").feature("relg1").set("InitialPolarizationType", "FullyPolarized");
    model.component("comp2").physics("gop").feature("relg1").set("axy0", 0);
    model.component("comp2").physics("gop").feature("relg1").set("az0", 1);

    model.study().create("std2");
    model.study("std2").create("rtrac", "RayTracing");
    model.study("std2").feature("rtrac").setSolveFor("/physics/ewfd", false);
    model.study("std2").feature("rtrac").setSolveFor("/physics/gop", true);
    model.study("std2").feature("rtrac").set("tunit", "ps");
    model.study("std2").feature("rtrac").set("tlist", "0 1");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("ray1", "Ray");
    model.result().dataset("ray1").set("solution", "sol2");
    model.result().dataset("ray1").set("posdof", new String[]{"comp2.qx", "comp2.qy"});
    model.result().dataset("ray1").set("geom", "geom2");
    model.result().dataset("ray1").set("rgeom", "pgeom_gop");
    model.result().dataset("ray1").set("rgeomspec", "fromphysics");
    model.result().dataset("ray1").set("physicsinterface", "gop");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "ray1");
    model.result("pg3").setIndex("looplevel", 2, 0);
    model.result("pg3").label("\u5c04\u7ebf\u8f68\u8ff9 (gop)");
    model.result("pg3").create("rtrj1", "RayTrajectories");
    model.result("pg3").feature("rtrj1").set("linetype", "line");
    model.result("pg3").feature("rtrj1").create("col1", "Color");
    model.result("pg3").feature("rtrj1").feature("col1").set("expr", "gop.I");
    model.result("pg3").feature("rtrj1").create("filt1", "RayTrajectoriesFilter");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u900f\u5c04\u7387\u548c\u53cd\u5c04\u7387\uff08ewfd \u548c gop\uff09");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u5165\u5c04\u89d2 (deg)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u900f\u5c04\u7387\u548c\u53cd\u5c04\u7387");
    model.result("pg4").set("legendpos", "middleleft");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "ewfd.Rorder_0+ewfd.Rorder_n1_op+ewfd.Rorder_p1_op", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "ewfd.Torder_0+ewfd.Torder_n1_op+ewfd.Torder_p1_op", 1);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "alpha");
    model.result("pg4").feature("glob1").set("xdataunit", "\u00b0");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u53cd\u5c04\u7684 WO", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "\u900f\u5c04\u7684 WO", 1);
    model.result("pg4").run();
    model.result("pg4").create("rtp1", "Ray1D");
    model.result("pg4").feature("rtp1").set("markerpos", "datapoints");
    model.result("pg4").feature("rtp1").set("linewidth", "preference");
    model.result("pg4").feature("rtp1").set("data", "ray1");
    model.result("pg4").feature("rtp1").setIndex("looplevelinput", "last", 0);
    model.result("pg4").feature("rtp1").set("expr", "gop.Qgr");
    model.result("pg4").feature("rtp1").set("xdata", "expr");
    model.result("pg4").feature("rtp1").set("xdataexpr", "at(0,alpha_ro)");
    model.result("pg4").feature("rtp1").set("xdataunit", "\u00b0");
    model.result("pg4").feature("rtp1").set("linestyle", "none");
    model.result("pg4").feature("rtp1").set("linemarker", "point");
    model.result("pg4").feature("rtp1").set("markerpos", "interp");
    model.result("pg4").feature("rtp1").set("markers", 40);
    model.result("pg4").feature("rtp1").set("legend", true);
    model.result("pg4").feature("rtp1").set("legendmethod", "manual");
    model.result("pg4").feature("rtp1").setIndex("legends", "\u53cd\u5c04\u7684 RO", 0);
    model.result("pg4").feature().duplicate("rtp2", "rtp1");
    model.result("pg4").run();
    model.result("pg4").feature("rtp2").set("expr", "gop.Qgt");
    model.result("pg4").feature("rtp2").setIndex("legends", "\u900f\u5c04\u7684 RO", 0);
    model.result("pg4").run();

    model.title("\u884d\u5c04\u5149\u6805");

    model
         .description("\u672c\u4f8b\u91c7\u7528\u201c\u6ce2\u52a8\u5149\u5b66\u6a21\u5757\u201d\u548c\u201c\u5c04\u7ebf\u5149\u5b66\u6a21\u5757\u201d\u5bf9\u4e0d\u540c\u5165\u5c04\u89d2\u5ea6\u7684\u5149\u7ebf\u901a\u8fc7\u884d\u5c04\u5149\u6805\u7684\u4f20\u64ad\u8fdb\u884c\u5efa\u6a21\uff0c\u4f7f\u7528\u7531\u201c\u7535\u78c1\u6ce2\uff0c\u9891\u57df\u201d\u63a5\u53e3\u5728\u4e00\u4e2a\u5355\u4f4d\u5149\u6805\u4e2d\u8ba1\u7b97\u7684 S \u53c2\u6570\u6765\u6307\u5b9a\u201c\u51e0\u4f55\u5149\u5b66\u201d\u63a5\u53e3\u4e2d\u7684\u6bcf\u4e2a\u884d\u5c04\u7ea7\u7684\u53cd\u5c04\u7387\u548c\u900f\u5c04\u7387\uff0c\u53ef\u4ee5\u5728\u8fdc\u5927\u4e8e\u5355\u4f4d\u5149\u6805\u5bbd\u5ea6\u7684\u957f\u5ea6\u5c3a\u5ea6\u4e0a\u6a21\u62df\u5c04\u7ebf\u4f20\u64ad\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("diffraction_grating.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

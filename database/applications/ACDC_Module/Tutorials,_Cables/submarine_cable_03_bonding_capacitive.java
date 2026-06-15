/*
 * submarine_cable_03_bonding_capacitive.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class submarine_cable_03_bonding_capacitive {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Tutorials,_Cables");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ec", "ConductiveMedia", "geom1");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/ec", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570 1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Dcon", "26.2[mm]", "\u4e3b\u5bfc\u4f53\u76f4\u5f84\uff08\u76f8\uff09");
    model.param().set("Tins", "24.0[mm]", "\u7edd\u7f18\u539a\u5ea6 (XLPE)");
    model.param().set("Dins", "77.6[mm]", "\u7edd\u7f18\u76f4\u5f84\uff08XLPE \u548c SCC\uff09");
    model.param().set("Tscc", "(Dins/2-Dcon/2-Tins)/2", "\u534a\u5bfc\u4f53\u5316\u5408\u7269\u539a\u5ea6");
    model.param().set("Tpbs", "2.9[mm]", "\u94c5\u5305\u539a\u5ea6");
    model.param().set("Tpe", "2.9[mm]", "\u805a\u4e59\u70ef\u62a4\u5957\u539a\u5ea6");
    model.param()
         .set("Dpha", "Dins+2*Tpbs+2*Tpe", "\u76f8\u4f4d\u76f4\u5f84\uff08\u5305\u542b\u62a4\u5957\u548c PE\uff09");
    model.param().set("Dpha3", "Dpha*(2/sqrt(3)+1)", "\u4e09\u76f8\u7ed3\u5408\u7684\u76f4\u5f84");
    model.param().set("Dfic", "2.5[mm]", "\u5149\u7ea4\u7ea4\u82af\u76f4\u5f84");
    model.param().set("Tfih", "0.5[mm]", "\u94a2\u87ba\u65cb\u7ebf\u539a\u5ea6\uff08\u5149\u7ea4\uff09");
    model.param().set("Dfib", "9.2[mm]", "\u5149\u7f06\u76f4\u5f84");
    model.param().set("Dcab", "219.0[mm]", "\u6d77\u5e95\u7535\u7f06\u5916\u5f84");
    model.param().set("Darm", "(Dcab+Dpha3)/2", "\u94e0\u88c5\u73af\u4e2d\u5fc3\u76f4\u5f84");
    model.param().set("Tarm", "5.6[mm]", "\u94e0\u88c5\u539a\u5ea6\uff08\u7ebf\u5f84\uff09");
    model.param().set("Narm", "110", "\u73af\u4e2d\u7684\u94e0\u88c5\u7ebf\u6570");
    model.param().set("mfil", "0.5[mm]", "\u586b\u6599\u4f59\u91cf");
    model.param().set("marm", "pi*Darm/Narm-Tarm", "\u94e0\u88c5\u4f59\u91cf");
    model.param().create("par2");
    model.param("par2").label("\u51e0\u4f55\u53c2\u6570 2");

//    To import content from file, use:
//    model.param("par2").loadFile("FILENAME");
    model.param("par2")
         .set("Acon", "500[mm^2]", "\u4e3b\u5bfc\u4f53\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2")
         .set("Ncon", "Acon/(pi*(Dcon/2)^2)", "\u5bfc\u4f53\u5806\u79ef\u5bc6\u5ea6\uff08\u76f8\uff09");
    model.param("par2")
         .set("Apbs", "pi*(Dins+Tpbs)*Tpbs", "\u94c5\u5305\u7684\u6a2a\u622a\u9762\u79ef\uff08\u6bcf\u76f8\uff09");
    model.param("par2").set("Lsec1", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 1");
    model.param("par2")
         .set("Lsec2", "1-Lsec1-Lsec3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 2");
    model.param("par2").set("Lsec3", "1/3", "\u4ea4\u53c9\u4e92\u8054\u6bb5\u7684\u76f8\u5bf9\u957f\u5ea6 3");
    model.param("par2").set("Lcab", "10[km]", "\u6d77\u5e95\u7535\u7f06\u603b\u957f");
    model.param("par2")
         .set("Scab", "1e5", "\u51e0\u4f55\u6bd4\u4f8b\u56e0\u5b50\uff08\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u6a21\u578b\uff09");
    model.param().create("par3");
    model.param("par3").label("\u7535\u78c1\u53c2\u6570");

//    To import content from file, use:
//    model.param("par3").loadFile("FILENAME");
    model.param("par3").set("f0", "50[Hz]", "\u5de5\u4f5c\u9891\u7387");
    model.param("par3").set("w0", "(2*pi*f0[1/Hz])[rad/s]", "\u89d2\u9891\u7387");
    model.param("par3").set("V0", "220[kV]/sqrt(3)", "\u76f8\u5bf9\u5730\u7535\u538b\uff08\u5e45\u503c\uff09");
    model.param("par3").set("I0", "655[A]*sqrt(2)", "\u989d\u5b9a\u7535\u6d41\uff08\u5e45\u503c\uff09");
    model.param("par3").set("Scup", "5.96e7[S/m]", "\u94dc\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Spbs", "4.55e6[S/m]", "\u94c5\u5305\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Sarm", "4.03e6[S/m]", "\u94e0\u88c5\u7ebf\u7535\u5bfc\u7387\uff0c20\u00b0C");
    model.param("par3").set("Mcup", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94dc");
    model.param("par3").set("Mpbs", "1", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94c5\u5305");
    model.param("par3").set("Marm", "100-50*j", "\u76f8\u5bf9\u78c1\u5bfc\u7387\uff0c\u94e0\u88c5\u7ebf");
    model.param("par3")
         .set("Dscup", "min(1/real(sqrt(j*w0*mu0_const*Mcup*Scup)),Dcon/3)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94dc\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Dspbs", "min(1/real(sqrt(j*w0*mu0_const*Mpbs*Spbs)),12*Tpbs)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94c5\u5305\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Dsarm", "min(1/real(sqrt(j*w0*mu0_const*Marm*Sarm)),Tarm/2)", "\u96c6\u80a4\u6df1\u5ea6\uff0c\u94e0\u88c5\u7ebf\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Rcon", "1/Acon/Scup", "\u6bcf\u76f8\u4e3b\u5bfc\u4f53\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Rpbs", "1/Apbs/Spbs", "\u6bcf\u76f8\u94c5\u5305\u76f4\u6d41\u7535\u963b\uff0c20\u00b0C\uff08\u89e3\u6790\uff09");
    model.param("par3")
         .set("Exlpe", "2.5", "\u76f8\u5bf9\u4ecb\u7535\u5e38\u6570 XLPE\uff08\u53d6\u81ea IEC 60287\uff09");
    model.param("par3")
         .set("Cpha", "2*pi*epsilon0_const*Exlpe/log((Dins/2-Tscc)/(Dcon/2+Tscc))", "\u6bcf\u76f8\u7535\u5bb9\uff08\u89e3\u6790\uff09");
    model.param("par3").set("Icpha", "w0*Cpha*V0", "\u6bcf\u76f8\u5145\u7535\u7535\u6d41\uff08\u89e3\u6790\uff09");

    model.component("comp1").coordSystem().create("sys2", "Scaling");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("sys2").selection().all();
    model.component("comp1").coordSystem("sys2").setIndex("map", "Scab*z", 2);

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"Dins/2+Tpbs-Dcon/2", "1"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("size", "Lcab/Scab", 1);
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"Dcon/2", "0"});
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Tscc", 0);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Tins", 1);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "Tscc", 2);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showmaterial", true);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u534a\u5bfc\u4f53\u5316\u5408\u7269");
    model.component("comp1").material("mat1").set("color", "black");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").label("\u4ea4\u8054\u805a\u4e59\u70ef (XLPE)");
    model.component("comp1").material("mat2").selection().set(2);
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").label("\u94c5");
    model.component("comp1").material("mat3").selection().set(4);
    model.component("comp1").material("mat3").set("family", "lead");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"2[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("relpermittivity", new String[]{"2.25"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"1e-18[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("relpermittivity", new String[]{"Exlpe"});
    model.component("comp1").material("mat3").propertyGroup("def").set("electricconductivity", new String[]{"Spbs"});
    model.component("comp1").material("mat3").propertyGroup("def").set("relpermittivity", new String[]{"1"});

    model.component("comp1").physics("ec").create("cucn2", "CurrentConservation", 2);
    model.component("comp1").physics("ec").feature("cucn2").selection().set(4);
    model.component("comp1").physics("ec").feature("cucn2").set("sigma_mat", "userdef");
    model.component("comp1").physics("ec").feature("cucn2")
         .set("sigma", new String[]{"Spbs/Scab", "0", "0", "0", "Spbs", "0", "0", "0", "Spbs"});
    model.component("comp1").physics("ec").create("gnd1", "Ground", 1);
    model.component("comp1").physics("ec").feature("gnd1").selection().set(11);
    model.component("comp1").physics("ec").create("pot1", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot1").label("\u76f8 1");
    model.component("comp1").physics("ec").feature("pot1").selection().set(1);
    model.component("comp1").physics("ec").feature("pot1").set("V0", "(V0-(I0*Rcon*sys2.z))");

    model.study("std1").feature("freq").set("plist", "f0");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u52bf (ec)");
    model.result("pg1").set("dataisaxisym", "off");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").set("showlegendsmaxmin", true);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "Dipole");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("str1", "Streamline");
    model.result("pg1").feature("str1").set("showsolutionparams", "on");
    model.result("pg1").feature("str1").set("solutionparams", "parent");
    model.result("pg1").feature("str1").set("expr", new String[]{"ec.Er", "ec.Ez"});
    model.result("pg1").feature("str1").set("titletype", "none");
    model.result("pg1").feature("str1").set("posmethod", "uniform");
    model.result("pg1").feature("str1").set("udist", 0.02);
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
    model.result("pg1").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    model.result("pg1").feature("str1").set("inheritplot", "surf1");
    model.result("pg1").feature("str1").feature().create("col1", "Color");
    model.result("pg1").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg1").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg1").feature("str1").feature().create("filt1", "Filter");
    model.result("pg1").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset("rev1").set("data", "none");
    model.result().dataset("rev1").set("startangle", -90);
    model.result().dataset("rev1").set("revangle", 225);
    model.result().dataset("rev1").set("modenumber", "comp1.ec.m");
    model.result().dataset("rev1").set("data", "dset1");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u7535\u52bf\uff0c\u56de\u8f6c\u51e0\u4f55 (ec)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("solutionparams", "parent");
    model.result("pg2").feature("vol1").set("colortable", "Dipole");
    model.result("pg2").feature("vol1").set("showsolutionparams", "on");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (ec)");
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "ec.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"ec.Er", "ec.Ez"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "ec.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u52bf\u6a21\uff0c\u4e00\u7ef4 (ec)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().set(13);
    model.result("pg4").feature("lngr1").set("expr", "abs(V)");
    model.result("pg4").feature("lngr1").set("descractive", true);
    model.result("pg4").feature("lngr1").set("descr", "\u94c5\u5305\u4e0a\u7684\u538b\u5347");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "sys2.z");
    model.result("pg4").feature("lngr1").set("xdataunit", "km");
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u6d41\u6a21\uff0c\u4e00\u7ef4 (ec)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(13);
    model.result("pg5").feature("lngr1").set("expr", "ec.normJ*Apbs");
    model.result("pg5").feature("lngr1").set("descractive", true);
    model.result("pg5").feature("lngr1").set("descr", "\u901a\u8fc7\u94c5\u5305\u7684\u5145\u7535\u7535\u6d41");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "sys2.z");
    model.result("pg5").feature("lngr1").set("xdataunit", "km");
    model.result("pg5").run();
    model.result().numerical().create("int1", "IntSurface");
    model.result().numerical("int1").set("intvolume", true);
    model.result().numerical("int1").label("\u7535\u963b\u635f\u8017");
    model.result().numerical("int1").selection().all();
    model.result().numerical("int1").setIndex("expr", "ec.Qh*Scab", 0);
    model.result().numerical("int1").setIndex("descr", "\u7535\u963b\u635f\u8017", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u7535\u963b\u635f\u8017");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();

    model.component("comp1").physics("ec").feature("gnd1").selection().set(11, 12);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();

    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Lsec1*Lcab/Scab", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "Lsec2*Lcab/Scab", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("ec").feature("pot1").selection().set(5);
    model.component("comp1").physics("ec").create("pot2", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot2").label("\u76f8 2");
    model.component("comp1").physics("ec").feature("pot2").selection().set(3);
    model.component("comp1").physics("ec").feature("pot2").set("V0", "(V0-(I0*Rcon*sys2.z))*exp(-120[deg]*j)");
    model.component("comp1").physics("ec").create("pot3", "ElectricPotential", 1);
    model.component("comp1").physics("ec").feature("pot3").label("\u76f8 3");
    model.component("comp1").physics("ec").feature("pot3").selection().set(1);
    model.component("comp1").physics("ec").feature("pot3").set("V0", "(V0-(I0*Rcon*sys2.z))*exp(+120[deg]*j)");
    model.component("comp1").physics("ec").create("ein2", "ElectricInsulation", 1);
    model.component("comp1").physics("ec").feature("ein2").selection().set(4, 6, 11, 13, 18, 20);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("str1").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").run();

    model.view("view2").set("showgrid", false);
    model.view("view2").set("showaxisorientation", false);

    model.result("pg1").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
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
    model.result().export("anim1").set("sweeptype", "dde");
    model.result().export("anim1").set("maxframes", 6);
    model.result().export("anim1").run();
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("maxframes", 60);
    model.result().export("anim1").set("repeat", "forever");
    model.result("pg1").run();
    model.result("pg1").run();

    model.view("view2").set("showgrid", true);
    model.view("view2").set("showaxisorientation", true);

    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").run();
    model.result("pg3").feature("str1").active(false);
    model.result("pg5").run();

    model.title("\u6d77\u5e95\u7535\u7f06 3 - \u4e92\u8054\u7535\u5bb9");

    model
         .description("\u6839\u636e\u201c\u7535\u5bb9\u6548\u5e94\u201d\u6559\u7a0b\uff08\u672c\u7cfb\u5217\u7684\u524d\u4e00\u7bc7\u6559\u7a0b\uff09\u7684\u7ed3\u679c\uff0c\u6211\u4eec\u6709\u7406\u7531\u5ffd\u7565\u5404\u5c4f\u853d\u5c42\u4e4b\u95f4\u7684\u7535\u5bb9\u8026\u5408\uff0c\u800c\u8003\u8651\u5355\u4e00\u79bb\u76f8\u53ca\u5176\u5c4f\u853d\u5c42\u3002\u4e0e\u201c\u7535\u5bb9\u6548\u5e94\u201d\u3001\u201c\u7535\u611f\u6548\u5e94\u201d\u548c\u201c\u70ed\u6548\u5e94\u201d\u6559\u7a0b\u4e0d\u540c\uff0c\u672c\u6559\u7a0b\u91c7\u7528\u4e8c\u7ef4\u8f74\u5bf9\u79f0\u51e0\u4f55\u6765\u8868\u793a\u6574\u4e2a 10\u00a0\u516c\u91cc\u957f\u7684\u7535\u7f06\u3002\n\n\u672c\u4f8b\u5206\u6790\u591a\u79cd\u4e92\u8054\u7c7b\u578b\u4e2d\uff0c\u5145\u7535\u7535\u6d41\u7684\u7d2f\u79ef\u548c\u5c4f\u853d\u5c42\u4e2d\u76f8\u5e94\u7684\u635f\u8017\uff08\u5305\u542b\u9a8c\u8bc1\uff09\u3002\u8be5\u6a21\u578b\u9a8c\u8bc1\u4e86\u9ad8\u76f8\u7535\u52bf\u4f1a\u611f\u5e94\u4ea7\u751f\u5747\u5300\u5145\u7535\u7535\u6d41\u8fd9\u4e00\u5047\u8bbe - \u8fd9\u79cd\u7535\u6d41\u51e0\u4e4e\u4e0e\u5c4f\u853d\u5c42\u7535\u52bf\u65e0\u5173 - \u56e0\u6b64\u8bc1\u660e\u201c\u7535\u5bb9\u6548\u5e94\u201d\u548c\u201c\u7535\u611f\u6548\u5e94\u201d\u6559\u7a0b\uff08\u7b2c 2 \u7ae0\u548c\u7b2c 4 \u7ae0\uff09\u4e2d\u9009\u62e9\u7684\u65b9\u6cd5\u662f\u6b63\u786e\u7684\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("submarine_cable_03_bonding_capacitive.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}

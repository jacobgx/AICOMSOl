/*
 * photonic_crystal_demultiplexer_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:36 by COMSOL 6.3.0.290. */
public class photonic_crystal_demultiplexer_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ewfd", "ElectromagneticWavesFrequencyDomain", "geom1");

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
    model.study("std1").feature("wave").setSolveFor("/physics/ewfd", true);

    model.component("comp1").geom("geom1")
         .insertFile("photonic_crystal_demultiplexer_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7a7a\u6c14");
    model.component("comp1").material("mat1").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "Refractive_index");
    model.component("comp1").material("mat1").propertyGroup("RefractiveIndex").set("n", new String[]{"1"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("RefractiveIndex", "RefractiveIndex", "\u6298\u5c04\u7387");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func()
         .create("int2", "Interpolation");
    model.component("comp1").material("mat2")
         .label("GaAs (Gallium arsenide) (Papatryfonos et al. 2021: n,k 0.260-1.88 um)");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1").set("funcname", "nr");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("table", new String[][]{{"0.26049", "3.43205E+00"}, 
         {"0.26159", "3.49606E+00"}, 
         {"0.26270", "3.55371E+00"}, 
         {"0.26381", "3.60654E+00"}, 
         {"0.26494", "3.65273E+00"}, 
         {"0.26608", "3.69534E+00"}, 
         {"0.26723", "3.73572E+00"}, 
         {"0.26838", "3.77466E+00"}, 
         {"0.26955", "3.81255E+00"}, 
         {"0.27073", "3.85100E+00"}, 
         {"0.27191", "3.88906E+00"}, 
         {"0.27311", "3.92570E+00"}, 
         {"0.27432", "3.95920E+00"}, 
         {"0.27554", "3.98624E+00"}, 
         {"0.27677", "4.00656E+00"}, 
         {"0.27801", "4.02017E+00"}, 
         {"0.27926", "4.02559E+00"}, 
         {"0.28053", "4.02430E+00"}, 
         {"0.28180", "4.01657E+00"}, 
         {"0.28309", "4.00489E+00"}, 
         {"0.28439", "3.98902E+00"}, 
         {"0.28570", "3.96990E+00"}, 
         {"0.28702", "3.94939E+00"}, 
         {"0.28835", "3.92597E+00"}, 
         {"0.28970", "3.90179E+00"}, 
         {"0.29106", "3.87715E+00"}, 
         {"0.29244", "3.85202E+00"}, 
         {"0.29382", "3.82681E+00"}, 
         {"0.29522", "3.80272E+00"}, 
         {"0.29663", "3.77926E+00"}, 
         {"0.29806", "3.75660E+00"}, 
         {"0.29950", "3.73487E+00"}, 
         {"0.30095", "3.71427E+00"}, 
         {"0.30242", "3.69528E+00"}, 
         {"0.30390", "3.67681E+00"}, 
         {"0.30540", "3.65930E+00"}, 
         {"0.30691", "3.64294E+00"}, 
         {"0.30844", "3.62778E+00"}, 
         {"0.30998", "3.61349E+00"}, 
         {"0.31154", "3.60025E+00"}, 
         {"0.31311", "3.58797E+00"}, 
         {"0.31470", "3.57682E+00"}, 
         {"0.31631", "3.56678E+00"}, 
         {"0.31793", "3.55791E+00"}, 
         {"0.31957", "3.54997E+00"}, 
         {"0.32122", "3.54276E+00"}, 
         {"0.32290", "3.53647E+00"}, 
         {"0.32459", "3.53138E+00"}, 
         {"0.32630", "3.52709E+00"}, 
         {"0.32802", "3.52373E+00"}, 
         {"0.32977", "3.52125E+00"}, 
         {"0.33153", "3.51961E+00"}, 
         {"0.33331", "3.51910E+00"}, 
         {"0.33512", "3.51958E+00"}, 
         {"0.33694", "3.52105E+00"}, 
         {"0.33878", "3.52363E+00"}, 
         {"0.34064", "3.52704E+00"}, 
         {"0.34252", "3.53132E+00"}, 
         {"0.34442", "3.53694E+00"}, 
         {"0.34635", "3.54380E+00"}, 
         {"0.34829", "3.55161E+00"}, 
         {"0.35026", "3.56058E+00"}, 
         {"0.35225", "3.57093E+00"}, 
         {"0.35426", "3.58246E+00"}, 
         {"0.35630", "3.59541E+00"}, 
         {"0.35836", "3.60991E+00"}, 
         {"0.36044", "3.62589E+00"}, 
         {"0.36255", "3.64421E+00"}, 
         {"0.36468", "3.66468E+00"}, 
         {"0.36684", "3.68747E+00"}, 
         {"0.36903", "3.71315E+00"}, 
         {"0.37124", "3.74297E+00"}, 
         {"0.37347", "3.77707E+00"}, 
         {"0.37574", "3.81787E+00"}, 
         {"0.37803", "3.86684E+00"}, 
         {"0.38035", "3.92505E+00"}, 
         {"0.38269", "3.99982E+00"}, 
         {"0.38507", "4.08670E+00"}, 
         {"0.38748", "4.17867E+00"}, 
         {"0.38991", "4.26329E+00"}, 
         {"0.39238", "4.32937E+00"}, 
         {"0.39488", "4.37664E+00"}, 
         {"0.39741", "4.41240E+00"}, 
         {"0.39998", "4.44075E+00"}, 
         {"0.40257", "4.46702E+00"}, 
         {"0.40520", "4.49714E+00"}, 
         {"0.40787", "4.54333E+00"}, 
         {"0.41057", "4.61656E+00"}, 
         {"0.41331", "4.72058E+00"}, 
         {"0.41608", "4.85265E+00"}, 
         {"0.41889", "4.96468E+00"}, 
         {"0.42174", "5.02373E+00"}, 
         {"0.42463", "5.03903E+00"}, 
         {"0.42756", "5.02004E+00"}, 
         {"0.43053", "4.98074E+00"}, 
         {"0.43354", "4.93203E+00"}, 
         {"0.43659", "4.88076E+00"}, 
         {"0.43969", "4.82963E+00"}, 
         {"0.44283", "4.77962E+00"}, 
         {"0.44602", "4.73089E+00"}, 
         {"0.44925", "4.68558E+00"}, 
         {"0.45253", "4.64157E+00"}, 
         {"0.45586", "4.59938E+00"}, 
         {"0.45923", "4.55929E+00"}, 
         {"0.46266", "4.52144E+00"}, 
         {"0.46614", "4.48543E+00"}, 
         {"0.46967", "4.45063E+00"}, 
         {"0.47325", "4.41771E+00"}, 
         {"0.47689", "4.38605E+00"}, 
         {"0.48059", "4.35607E+00"}, 
         {"0.48435", "4.32739E+00"}, 
         {"0.48816", "4.29988E+00"}, 
         {"0.49203", "4.27343E+00"}, 
         {"0.49597", "4.24774E+00"}, 
         {"0.49997", "4.22320E+00"}, 
         {"0.50403", "4.19966E+00"}, 
         {"0.50817", "4.17705E+00"}, 
         {"0.51237", "4.15524E+00"}, 
         {"0.51664", "4.13387E+00"}, 
         {"0.52098", "4.11371E+00"}, 
         {"0.52539", "4.09432E+00"}, 
         {"0.52988", "4.07533E+00"}, 
         {"0.53445", "4.05701E+00"}, 
         {"0.53910", "4.03901E+00"}, 
         {"0.54383", "4.02185E+00"}, 
         {"0.54864", "4.00542E+00"}, 
         {"0.55354", "3.98939E+00"}, 
         {"0.55853", "3.97389E+00"}, 
         {"0.56360", "3.95906E+00"}, 
         {"0.56877", "3.94467E+00"}, 
         {"0.57404", "3.93106E+00"}, 
         {"0.57940", "3.91783E+00"}, 
         {"0.58487", "3.90469E+00"}, 
         {"0.59044", "3.89200E+00"}, 
         {"0.59612", "3.87986E+00"}, 
         {"0.60191", "3.86815E+00"}, 
         {"0.60781", "3.85669E+00"}, 
         {"0.61382", "3.84603E+00"}, 
         {"0.61996", "3.83524E+00"}, 
         {"0.62623", "3.82498E+00"}, 
         {"0.63262", "3.81546E+00"}, 
         {"0.63914", "3.80690E+00"}, 
         {"0.64579", "3.79813E+00"}, 
         {"0.65259", "3.79034E+00"}, 
         {"0.65954", "3.78184E+00"}, 
         {"0.66663", "3.77177E+00"}, 
         {"0.67387", "3.75979E+00"}, 
         {"0.68128", "3.74792E+00"}, 
         {"0.68885", "3.73689E+00"}, 
         {"0.69659", "3.72659E+00"}, 
         {"0.70450", "3.71666E+00"}, 
         {"0.71260", "3.70768E+00"}, 
         {"0.72089", "3.70001E+00"}, 
         {"0.72937", "3.69257E+00"}, 
         {"0.73805", "3.68538E+00"}, 
         {"0.74694", "3.67856E+00"}, 
         {"0.75605", "3.67249E+00"}, 
         {"0.76539", "3.66659E+00"}, 
         {"0.77495", "3.66130E+00"}, 
         {"0.78476", "3.65752E+00"}, 
         {"0.79482", "3.65601E+00"}, 
         {"0.80515", "3.64806E+00"}, 
         {"0.81574", "3.62635E+00"}, 
         {"0.82662", "3.60509E+00"}, 
         {"0.83779", "3.58756E+00"}, 
         {"0.84926", "3.57142E+00"}, 
         {"0.86106", "3.56115E+00"}, 
         {"0.87319", "3.55095E+00"}, 
         {"0.88566", "3.54215E+00"}, 
         {"0.89850", "3.53347E+00"}, 
         {"0.91171", "3.52476E+00"}, 
         {"0.92532", "3.51557E+00"}, 
         {"0.93934", "3.50642E+00"}, 
         {"0.95379", "3.49757E+00"}, 
         {"0.96869", "3.48877E+00"}, 
         {"0.98407", "3.48010E+00"}, 
         {"0.99994", "3.47343E+00"}, 
         {"1.01633", "3.46678E+00"}, 
         {"1.03327", "3.46037E+00"}, 
         {"1.05078", "3.45399E+00"}, 
         {"1.06890", "3.44767E+00"}, 
         {"1.08765", "3.44250E+00"}, 
         {"1.10708", "3.43736E+00"}, 
         {"1.12721", "3.43236E+00"}, 
         {"1.14808", "3.42740E+00"}, 
         {"1.16974", "3.42251E+00"}, 
         {"1.19224", "3.41848E+00"}, 
         {"1.21561", "3.41446E+00"}, 
         {"1.23993", "3.41057E+00"}, 
         {"1.26523", "3.40731E+00"}, 
         {"1.29159", "3.40587E+00"}, 
         {"1.31907", "3.40247E+00"}, 
         {"1.34775", "3.39611E+00"}, 
         {"1.37770", "3.39284E+00"}, 
         {"1.40901", "3.38961E+00"}, 
         {"1.44177", "3.38641E+00"}, 
         {"1.47610", "3.38363E+00"}, 
         {"1.51210", "3.38078E+00"}, 
         {"1.54991", "3.37793E+00"}, 
         {"1.58965", "3.37534E+00"}, 
         {"1.63148", "3.37799E+00"}, 
         {"1.67558", "3.37666E+00"}, 
         {"1.72212", "3.37408E+00"}, 
         {"1.77132", "3.37140E+00"}, 
         {"1.82342", "3.36872E+00"}, 
         {"1.87868", "3.36654E+00"}});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int1")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2").set("funcname", "ni");
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("table", new String[][]{{"0.26049", "3.70410E+00"}, 
         {"0.26159", "3.64117E+00"}, 
         {"0.26270", "3.57757E+00"}, 
         {"0.26381", "3.51408E+00"}, 
         {"0.26494", "3.45129E+00"}, 
         {"0.26608", "3.38972E+00"}, 
         {"0.26723", "3.32935E+00"}, 
         {"0.26838", "3.27031E+00"}, 
         {"0.26955", "3.21369E+00"}, 
         {"0.27073", "3.15591E+00"}, 
         {"0.27191", "3.09523E+00"}, 
         {"0.27311", "3.03074E+00"}, 
         {"0.27432", "2.96174E+00"}, 
         {"0.27554", "2.88868E+00"}, 
         {"0.27677", "2.81381E+00"}, 
         {"0.27801", "2.73806E+00"}, 
         {"0.27926", "2.66281E+00"}, 
         {"0.28053", "2.59006E+00"}, 
         {"0.28180", "2.52174E+00"}, 
         {"0.28309", "2.45728E+00"}, 
         {"0.28439", "2.39759E+00"}, 
         {"0.28570", "2.34234E+00"}, 
         {"0.28702", "2.29093E+00"}, 
         {"0.28835", "2.24486E+00"}, 
         {"0.28970", "2.20272E+00"}, 
         {"0.29106", "2.16454E+00"}, 
         {"0.29244", "2.13020E+00"}, 
         {"0.29382", "2.09947E+00"}, 
         {"0.29522", "2.07313E+00"}, 
         {"0.29663", "2.04943E+00"}, 
         {"0.29806", "2.02852E+00"}, 
         {"0.29950", "2.01023E+00"}, 
         {"0.30095", "1.99379E+00"}, 
         {"0.30242", "1.97977E+00"}, 
         {"0.30390", "1.96769E+00"}, 
         {"0.30540", "1.95727E+00"}, 
         {"0.30691", "1.94825E+00"}, 
         {"0.30844", "1.93981E+00"}, 
         {"0.30998", "1.93311E+00"}, 
         {"0.31154", "1.92791E+00"}, 
         {"0.31311", "1.92367E+00"}, 
         {"0.31470", "1.92011E+00"}, 
         {"0.31631", "1.91769E+00"}, 
         {"0.31793", "1.91633E+00"}, 
         {"0.31957", "1.91573E+00"}, 
         {"0.32122", "1.91584E+00"}, 
         {"0.32290", "1.91647E+00"}, 
         {"0.32459", "1.91829E+00"}, 
         {"0.32630", "1.92056E+00"}, 
         {"0.32802", "1.92347E+00"}, 
         {"0.32977", "1.92716E+00"}, 
         {"0.33153", "1.93129E+00"}, 
         {"0.33331", "1.93595E+00"}, 
         {"0.33512", "1.94137E+00"}, 
         {"0.33694", "1.94732E+00"}, 
         {"0.33878", "1.95364E+00"}, 
         {"0.34064", "1.96036E+00"}, 
         {"0.34252", "1.96789E+00"}, 
         {"0.34442", "1.97692E+00"}, 
         {"0.34635", "1.98420E+00"}, 
         {"0.34829", "1.99392E+00"}, 
         {"0.35026", "2.00292E+00"}, 
         {"0.35225", "2.01410E+00"}, 
         {"0.35426", "2.02527E+00"}, 
         {"0.35630", "2.03711E+00"}, 
         {"0.35836", "2.04836E+00"}, 
         {"0.36044", "2.05913E+00"}, 
         {"0.36255", "2.07072E+00"}, 
         {"0.36468", "2.08308E+00"}, 
         {"0.36684", "2.09605E+00"}, 
         {"0.36903", "2.10960E+00"}, 
         {"0.37124", "2.12445E+00"}, 
         {"0.37347", "2.14027E+00"}, 
         {"0.37574", "2.15726E+00"}, 
         {"0.37803", "2.17578E+00"}, 
         {"0.38035", "2.19609E+00"}, 
         {"0.38269", "2.21797E+00"}, 
         {"0.38507", "2.24157E+00"}, 
         {"0.38748", "2.26621E+00"}, 
         {"0.38991", "2.29044E+00"}, 
         {"0.39238", "2.30642E+00"}, 
         {"0.39488", "2.30637E+00"}, 
         {"0.39741", "2.28487E+00"}, 
         {"0.39998", "2.24214E+00"}, 
         {"0.40257", "2.18391E+00"}, 
         {"0.40520", "2.12482E+00"}, 
         {"0.40787", "2.07205E+00"}, 
         {"0.41057", "2.02894E+00"}, 
         {"0.41331", "1.99759E+00"}, 
         {"0.41608", "1.97753E+00"}, 
         {"0.41889", "1.97061E+00"}, 
         {"0.42174", "1.96602E+00"}, 
         {"0.42463", "1.94662E+00"}, 
         {"0.42756", "1.88758E+00"}, 
         {"0.43053", "1.74458E+00"}, 
         {"0.43354", "1.57944E+00"}, 
         {"0.43659", "1.41707E+00"}, 
         {"0.43969", "1.27015E+00"}, 
         {"0.44283", "1.14610E+00"}, 
         {"0.44602", "1.04502E+00"}, 
         {"0.44925", "9.61390E-01"}, 
         {"0.45253", "8.89840E-01"}, 
         {"0.45586", "8.28110E-01"}, 
         {"0.45923", "7.75750E-01"}, 
         {"0.46266", "7.28640E-01"}, 
         {"0.46614", "6.88030E-01"}, 
         {"0.46967", "6.51360E-01"}, 
         {"0.47325", "6.16930E-01"}, 
         {"0.47689", "5.86930E-01"}, 
         {"0.48059", "5.59970E-01"}, 
         {"0.48435", "5.36110E-01"}, 
         {"0.48816", "5.13500E-01"}, 
         {"0.49203", "4.91710E-01"}, 
         {"0.49597", "4.72080E-01"}, 
         {"0.49997", "4.53380E-01"}, 
         {"0.50403", "4.36300E-01"}, 
         {"0.50817", "4.20600E-01"}, 
         {"0.51237", "4.05180E-01"}, 
         {"0.51664", "3.91990E-01"}, 
         {"0.52098", "3.79230E-01"}, 
         {"0.52539", "3.66410E-01"}, 
         {"0.52988", "3.53750E-01"}, 
         {"0.53445", "3.42790E-01"}, 
         {"0.53910", "3.30670E-01"}, 
         {"0.54383", "3.19020E-01"}, 
         {"0.54864", "3.08750E-01"}, 
         {"0.55354", "2.98750E-01"}, 
         {"0.55853", "2.91260E-01"}, 
         {"0.56360", "2.83600E-01"}, 
         {"0.56877", "2.74900E-01"}, 
         {"0.57404", "2.66620E-01"}, 
         {"0.57940", "2.58370E-01"}, 
         {"0.58487", "2.50750E-01"}, 
         {"0.59044", "2.44100E-01"}, 
         {"0.59612", "2.35830E-01"}, 
         {"0.60191", "2.27180E-01"}, 
         {"0.60781", "2.18390E-01"}, 
         {"0.61382", "2.13400E-01"}, 
         {"0.61996", "2.07770E-01"}, 
         {"0.62623", "2.01580E-01"}, 
         {"0.63262", "1.95820E-01"}, 
         {"0.63914", "1.89550E-01"}, 
         {"0.64579", "1.83490E-01"}, 
         {"0.65259", "1.78060E-01"}, 
         {"0.65954", "1.72580E-01"}, 
         {"0.66663", "1.65900E-01"}, 
         {"0.67387", "1.59260E-01"}, 
         {"0.68128", "1.51840E-01"}, 
         {"0.68885", "1.43030E-01"}, 
         {"0.69659", "1.33120E-01"}, 
         {"0.70450", "1.25050E-01"}, 
         {"0.71260", "1.18710E-01"}, 
         {"0.72089", "1.13340E-01"}, 
         {"0.72937", "1.08510E-01"}, 
         {"0.73805", "1.03950E-01"}, 
         {"0.74694", "1.00230E-01"}, 
         {"0.75605", "9.59500E-02"}, 
         {"0.76539", "9.16300E-02"}, 
         {"0.77495", "8.71100E-02"}, 
         {"0.78476", "8.19200E-02"}, 
         {"0.79482", "7.80300E-02"}, 
         {"0.80515", "7.33100E-02"}, 
         {"0.81574", "6.75700E-02"}, 
         {"0.82662", "6.02500E-02"}, 
         {"0.83779", "5.00900E-02"}, 
         {"0.84926", "2.51200E-02"}, 
         {"0.86106", "1.14400E-02"}, 
         {"0.87319", "6.10000E-03"}, 
         {"0.88566", "4.05000E-03"}, 
         {"0.89850", "2.02400E-04"}, 
         {"0.91171", "9.71111E-05"}, 
         {"0.92532", "6.13333E-06"}, 
         {"0.93934", "0.00000E+00"}, 
         {"0.95379", "0.00000E+00"}, 
         {"0.96869", "0.00000E+00"}, 
         {"0.98407", "0.00000E+00"}, 
         {"0.99994", "0.00000E+00"}, 
         {"1.01633", "0.00000E+00"}, 
         {"1.03327", "0.00000E+00"}, 
         {"1.05078", "0.00000E+00"}, 
         {"1.06890", "0.00000E+00"}, 
         {"1.08765", "0.00000E+00"}, 
         {"1.10708", "0.00000E+00"}, 
         {"1.12721", "0.00000E+00"}, 
         {"1.14808", "0.00000E+00"}, 
         {"1.16974", "0.00000E+00"}, 
         {"1.19224", "0.00000E+00"}, 
         {"1.21561", "0.00000E+00"}, 
         {"1.23993", "0.00000E+00"}, 
         {"1.26523", "0.00000E+00"}, 
         {"1.29159", "0.00000E+00"}, 
         {"1.31907", "0.00000E+00"}, 
         {"1.34775", "0.00000E+00"}, 
         {"1.37770", "0.00000E+00"}, 
         {"1.40901", "0.00000E+00"}, 
         {"1.44177", "0.00000E+00"}, 
         {"1.47610", "0.00000E+00"}, 
         {"1.51210", "0.00000E+00"}, 
         {"1.54991", "0.00000E+00"}, 
         {"1.58965", "0.00000E+00"}, 
         {"1.63148", "0.00000E+00"}, 
         {"1.67558", "0.00000E+00"}, 
         {"1.72212", "0.00000E+00"}, 
         {"1.77132", "0.00000E+00"}, 
         {"1.82342", "0.00000E+00"}, 
         {"1.87868", "0.00000E+00"}});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").func("int2")
         .set("argunit", new String[]{"um"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("n", new String[]{"nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)", "0", "0", "0", "nr(c_const/freq)"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex")
         .set("ki", new String[]{"ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)", "0", "0", "0", "ni(c_const/freq)"});
    model.component("comp1").material("mat2").propertyGroup("RefractiveIndex").addInput("frequency");
    model.component("comp1").material("mat2").selection().named("geom1_c1_dom");

    model.component("comp1").physics("ewfd").prop("components").set("components", "outofplane");
    model.component("comp1").physics("ewfd").create("sctr1", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr1").selection().all();
    model.component("comp1").physics("ewfd").create("sctr2", "Scattering", 1);
    model.component("comp1").physics("ewfd").feature("sctr2").selection().named("geom1_ls1_bnd");
    model.component("comp1").physics("ewfd").feature("sctr2").set("IncidentField", "EField");
    model.component("comp1").physics("ewfd").feature("sctr2").set("E0i", new int[]{0, 0, 1});

    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").probe().create("bnd1", "Boundary");
    model.component("comp1").probe("bnd1").set("intsurface", true);
    model.component("comp1").probe("bnd1").label("\u529f\u7387\u7aef\u53e3 1");
    model.component("comp1").probe("bnd1").set("probename", "obj1");
    model.component("comp1").probe("bnd1").set("type", "integral");
    model.component("comp1").probe("bnd1").selection().named("geom1_ls2_bnd");
    model.component("comp1").probe("bnd1").set("expr", "ewfd.nPoav");
    model.component("comp1").probe("bnd1").set("descr", "\u529f\u7387\u6d41\u51fa\uff0c\u65f6\u95f4\u5e73\u5747");
    model.component("comp1").probe().duplicate("bnd2", "bnd1");
    model.component("comp1").probe("bnd2").label("\u529f\u7387\u7aef\u53e3 2");
    model.component("comp1").probe("bnd2").set("probename", "obj2");
    model.component("comp1").probe("bnd2").selection().named("geom1_ls3_bnd");

    model.param().create("par2");
    model.param("par2").label("\u6ce2\u53c2\u6570");
    model.param("par2").set("meshsz", "lambda2/9");
    model.param("par2").descr("meshsz", "\u7f51\u683c\u5927\u5c0f");
    model.param("par2").set("minPower", "0.25[nW/m]");
    model.param("par2").descr("minPower", "\u901a\u5e26\u529f\u7387");
    model.param("par2").set("maxPower", "minPower/5");
    model.param("par2").descr("maxPower", "\u6700\u5c0f\u5e26\u5916\u529f\u7387");
    model.param("par2").set("lambda1", "1[um]");
    model.param("par2").descr("lambda1", "\u7b2c\u4e00\u4e2a\u6ce2\u957f");
    model.param("par2").set("lambda3", "0.96*lambda1");
    model.param("par2").descr("lambda3", "\u5e26\u5916\u6ce2\u957f\uff08\u8f83\u4f4e\uff09");
    model.param("par2").set("lambda4", "1.055*lambda1");
    model.param("par2").descr("lambda4", "\u5e26\u5916\u6ce2\u957f\uff08\u5c45\u4e2d\uff09");
    model.param("par2").set("lambda5", "1.1175*lambda1");
    model.param("par2").descr("lambda5", "\u5e26\u5916\u6ce2\u957f\uff08\u8f83\u9ad8\uff09");
    model.param("par2").set("lambda6", "1.13*lambda1");
    model.param("par2").descr("lambda6", "\u5e26\u5916\u6ce2\u957f\uff08\u6700\u9ad8\uff09");
    model.param("par2").set("dWaveN", "3");
    model.param("par2").descr("dWaveN", "\u6bcf\u4e2a\u901a\u5e26\u7684\u9891\u7387");
    model.param("par2").set("dWaveN3", "4");
    model.param("par2").descr("dWaveN3", "\u7b2c\u4e00\u4e2a\u5e26\u5916\u7ea6\u675f\u7684\u9891\u7387");
    model.param("par2").set("dWaveN4", "7");
    model.param("par2").descr("dWaveN4", "\u4e2d\u95f4\u5e26\u5916\u7ea6\u675f\u7684\u9891\u7387");
    model.param("par2").set("dWaveN5", "6");
    model.param("par2").descr("dWaveN5", "\u7b2c\u4e09\u4e2a\u5e26\u5916\u7ea6\u675f\u7684\u9891\u7387");
    model.param("par2").set("dWaveN6", "5");
    model.param("par2").descr("dWaveN6", "\u6700\u540e\u4e00\u4e2a\u5e26\u5916\u7ea6\u675f\u7684\u9891\u7387");
    model.param("par2").set("dWave", "0.01*lambda1");
    model.param("par2").descr("dWave", "\u901a\u5e26\u7684\u5e26\u5bbd");
    model.param("par2").set("dWave3", "2*dWave");
    model.param("par2").descr("dWave3", "\u7b2c\u4e00\u4e2a\u5e26\u5916\u6ce2\u957f\u7684\u5e26\u5bbd");
    model.param("par2").set("dWave4", "2.5*dWave");
    model.param("par2").descr("dWave4", "\u4e2d\u95f4\u5e26\u5916\u6ce2\u957f\u7684\u5e26\u5bbd");
    model.param("par2").set("dWave5", "dWave/1.5");
    model.param("par2").descr("dWave5", "\u7b2c\u4e09\u4e2a\u5e26\u5916\u6ce2\u957f\u7684\u5e26\u5bbd");
    model.param("par2").set("dWave6", "1.5*dWave");
    model.param("par2").descr("dWave6", "\u6700\u540e\u4e00\u4e2a\u5e26\u5916\u6ce2\u957f\u7684\u5e26\u5bbd");

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").feature("size").set("hcurve", Double.POSITIVE_INFINITY);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_c1_dom");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "meshsz/2");
    model.component("comp1").mesh("mesh1").feature("size1").set("hmin", "meshsz/4");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("wave").set("punit", "m");
    model.study("std1").feature("wave").set("plist", "range(lambda1-50[nm],1[nm],lambda2+50[nm])");
    model.study("std1").feature("wave").set("probesel", "none");
    model.study("std1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u7535\u573a (ewfd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common("fsd1").selection().set(2);
    model.component("comp1").common().create("tsf1", "Transformation");
    model.component("comp1").common("tsf1").selection().named("geom1_comsel1");
    model.component("comp1").common("tsf1").setIndex("move_lock", false, 0);
    model.component("comp1").common("tsf1").setIndex("move_lBound", "-5E-8", 0);
    model.component("comp1").common("tsf1").setIndex("move_uBound", "5E-8", 0);
    model.component("comp1").common("tsf1").setIndex("move_lock", false, 1);
    model.component("comp1").common("tsf1").setIndex("move_lBound", "-5E-8", 1);
    model.component("comp1").common("tsf1").setIndex("move_uBound", "5E-8", 1);
    model.component("comp1").common("tsf1").set("scalingType", "No_scaling");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").label("\u76ee\u6807");
    model.component("comp1").variable("var1")
         .set("objA", "if(lambda0<(lambda1+lambda2)/2,-obj1/minPower,-obj2/minPower)");
    model.component("comp1").variable("var1").descr("objA", "\u901a\u5e26\u76ee\u6807");
    model.component("comp1").variable("var1").set("objB", "(obj1+obj2)/maxPower-2");
    model.component("comp1").variable("var1").descr("objB", "\u5e26\u5916\u76ee\u6807");
    model.component("comp1").variable("var1")
         .set("obj", "if(min(abs(lambda0-lambda1),abs(lambda0-lambda2))<dWave/1.99,objA,objB)");
    model.component("comp1").variable("var1").descr("obj", "\u76ee\u6807\u51fd\u6570");

    model.study().create("std2");
    model.study("std2").create("wave", "Wavelength");
    model.study("std2").feature("wave").set("plotgroup", "Default");
    model.study("std2").feature("wave").set("ftplistmethod", "manual");
    model.study("std2").feature("wave").set("solnum", "auto");
    model.study("std2").feature("wave").set("notsolnum", "auto");
    model.study("std2").feature("wave").set("outputmap", new String[]{});
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").set("ngenAUX", "1");
    model.study("std2").feature("wave").set("goalngenAUX", "1");
    model.study("std2").feature("wave").setSolveFor("/physics/ewfd", true);
    model.study().create("std3");
    model.study("std1").feature("wave").setSolveFor("/frame/material1", false);
    model.study("std2").feature("wave")
         .set("plist", "range(lambda3-dWave3/2,dWave/(dWaveN3-1),lambda3+dWave3/2) range(lambda1-dWave/2,dWave/(dWaveN-1),lambda1+dWave/2) range(lambda4-dWave4/2,dWave4/(dWaveN4-1),lambda4+dWave4/2) range(lambda2-dWave/2,dWave/(dWaveN-1),lambda2+dWave/2) range(lambda5-dWave5/2,dWave5/(dWaveN5-1),lambda5+dWave5/2) range(lambda6-dWave6/2,dWave6/(dWaveN6-1),lambda6+dWave6/2)");
    model.study("std2").feature("wave").set("probesel", "none");
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 50);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.obj"});
    model.study("std2").feature("sho").set("descr", new String[]{"\u76ee\u6807\u51fd\u6570"});
    model.study("std2").feature("sho").set("objectivesolution", "max");
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").label("\u5f62\u72b6\u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u573a (ewfd) 1");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("smooth", "internal");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result().create("pg3", "PlotGroup2D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u5f62\u72b6\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg3").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("frametype", "geometry");
    model.result("pg3").set("edgecolor", "gray");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").create("arwp1", "ArrowPoint");
    model.result("pg3").feature("arwp1").label("\u5e73\u79fb (\u53d8\u6362 1)");
    model.result("pg3").feature("arwp1").set("expr", new String[]{"tsf1.moveXg", "tsf1.moveYg"});
    model.result("pg3").feature("arwp1").set("arrowbase", "head");
    model.result("pg3").feature("arwp1").set("scaleactive", true);
    model.result("pg3").feature("arwp1").set("scale", "1");
    model.result("pg3").feature("arwp1").create("def1", "Deform");
    model.result("pg3").feature("arwp1").feature("def1")
         .set("expr", new String[]{"-tsf1.scaleXg-tsf1.rotateXg", "-tsf1.scaleYg-tsf1.rotateYg"});
    model.result("pg3").feature("arwp1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("arwp1").feature("def1").set("scale", "1");
    model.result("pg3").feature("arwp1").create("col1", "Color");
    model.result("pg3").feature("arwp1").feature("col1").set("expr", "tsf1.rel_move");
    model.result("pg3").feature("arwp1").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwp1").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwp1").feature("col1").set("rangecolormax", 1);
    model.result("pg3").create("arwp2", "ArrowPoint");
    model.result("pg3").feature("arwp2").label("\u7f29\u653e (\u53d8\u6362 1)");
    model.result("pg3").feature("arwp2").set("expr", new String[]{"tsf1.scaleXg", "tsf1.scaleYg"});
    model.result("pg3").feature("arwp2").set("arrowbase", "head");
    model.result("pg3").feature("arwp2").set("scaleactive", true);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("arwp2").set("scale", "1");
    model.result("pg3").feature("arwp2").set("inheritplot", "arwp1");
    model.result("pg3").feature("arwp2").create("def1", "Deform");
    model.result("pg3").feature("arwp2").feature("def1")
         .set("expr", new String[]{"-tsf1.rotateXg", "-tsf1.rotateYg"});
    model.result("pg3").feature("arwp2").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("arwp2").feature("def1").set("scale", "1");
    model.result("pg3").feature("arwp2").create("col1", "Color");
    model.result("pg3").feature("arwp2").feature("col1").set("expr", "tsf1.rel_scale");
    model.result("pg3").feature("arwp2").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwp2").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwp2").feature("col1").set("rangecolormax", 1);
    model.result("pg3").create("arwp3", "ArrowPoint");
    model.result("pg3").feature("arwp3").label("\u65cb\u8f6c (\u53d8\u6362 1)");
    model.result("pg3").feature("arwp3").set("expr", new String[]{"tsf1.rotateXg", "tsf1.rotateYg"});
    model.result("pg3").feature("arwp3").set("arrowbase", "head");
    model.result("pg3").feature("arwp3").set("scaleactive", true);
    model.result("pg3").feature("arwp3").set("scale", "1");
    model.result("pg3").feature("arwp3").set("inheritplot", "arwp1");
    model.result("pg3").feature("arwp3").create("col1", "Color");
    model.result("pg3").feature("arwp3").feature("col1").set("expr", "tsf1.rel_rotate");
    model.result("pg3").feature("arwp3").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwp3").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwp3").feature("col1").set("rangecolormax", 1);
    model.result("pg2").run();

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg3");

    model.sol("sol2").feature("o1").set("nojacmethod", false);
    model.sol("sol2").feature("o1").feature("s1").create("se1", "Segregated");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").set("segterm", "iter");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").create("ss1", "SegregatedStep");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef").label("\u4f18\u5316");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ssDef")
         .set("segvar", new String[]{"comp1_material_disp", "comp1_tsf1_move"});
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1").label("\u7535\u573a");
    model.sol("sol2").feature("o1").feature("s1").feature("se1").feature("ss1")
         .set("segvar", new String[]{"comp1_E", "comp1_tsf1_move"});
    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result().dataset("dset2").createDeformedConfig("deform1", "mesh2");

    model.study("std3").feature().copy("wave", "std1/wave");
    model.study("std3").feature("wave").set("useinitsol", true);
    model.study("std3").feature("wave").set("initmethod", "sol");
    model.study("std3").feature("wave").set("initstudy", "std2");
    model.study("std3").feature("wave").set("usesol", true);
    model.study("std3").feature("wave").set("notsolmethod", "sol");
    model.study("std3").feature("wave").set("notstudy", "std2");
    model.study("std3").feature("wave").setEntry("outputmap", "ewfd", "selection");
    model.study("std3").feature("wave").setEntry("outputselectionmap", "ewfd", "geom1_ls2_bnd;geom1_ls3_bnd");
    model.study("std3").feature("wave").setEntry("outputmap", "frame:material1", "selection");
    model.study("std3").feature("wave")
         .setEntry("outputselectionmap", "frame:material1", "geom1_ls2_bnd;geom1_ls3_bnd");
    model.study("std3").feature("wave").setEntry("mesh", "geom1", "mesh2");
    model.study("std3").label("\u9a8c\u8bc1");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg1").run();
    model.result("pg1").label("\u7535\u573a\uff08\u521d\u59cb\uff09");
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg1").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg1").feature("surf1").set("colortable", "WaveLight");
    model.result("pg2").run();
    model.result("pg2").create("str1", "Streamline");
    model.result("pg2").feature("str1").set("expr", new String[]{"ewfd.Poavx", "ewfd.Poavy"});
    model.result("pg2").feature("str1")
         .set("descr", "\u529f\u7387\u6d41\uff0c\u65f6\u95f4\u5e73\u5747 \uff08\u7a7a\u95f4\u548c\u6750\u6599\u5750\u6807\u7cfb\uff09");
    model.result("pg2").feature("str1").selection().named("geom1_ls1_bnd");
    model.result("pg2").feature("str1").set("linetype", "tube");
    model.result("pg2").feature("str1").set("tuberadiusscaleactive", true);
    model.result("pg2").feature("str1").set("radiusexpr", "3e-9");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("expr", "ewfd.Ez");
    model.result("pg2").feature("surf1").set("descr", "\u7535\u573a\uff0cz \u5206\u91cf");
    model.result("pg2").feature("surf1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("surf1").set("colortable", "WaveLight");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u573a\uff08\u4f18\u5316\u540e\uff09");
    model.result("pg2").setIndex("looplevel", 19, 0);
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 9, 0);
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u9891\u8c31");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "P (nW/m)");
    model.result("pg4").set("legendlayout", "outside");
    model.result("pg4").set("legendposoutside", "bottom");
    model.result("pg4").set("legendrowcount", 2);
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"obj1"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u529f\u7387\u7aef\u53e3 1"});
    model.result("pg4").feature("glob1").set("expr", new String[]{"obj1", "obj2"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u529f\u7387\u7aef\u53e3 1", "\u529f\u7387\u7aef\u53e3 2"});
    model.result("pg4").feature("glob1").setIndex("unit", "nW/m", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u529f\u7387\u7aef\u53e3 1 (mesh2)", 0);
    model.result("pg4").feature("glob1").setIndex("unit", "nW/m", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u529f\u7387\u7aef\u53e3 2 (mesh2)", 1);
    model.result("pg4").feature("glob1").set("xdataparamunit", "\u00b5m");
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").set("data", "dset2");
    model.result("pg4").feature("glob2").set("linestyle", "none");
    model.result("pg4").feature("glob2").set("linemarker", "square");
    model.result("pg4").feature("glob2").set("linecolor", "cyclereset");
    model.result("pg4").feature("glob2").setIndex("descr", "\u529f\u7387\u7aef\u53e3 1", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u529f\u7387\u7aef\u53e3 2", 1);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u7f29\u7565\u56fe");
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("linetype", "tube");
    model.result("pg5").feature("line1").set("radiusexpr", "5e-9");
    model.result("pg5").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg5").feature().duplicate("line2", "line1");
    model.result("pg5").run();
    model.result("pg5").feature("line2").set("color", "gray");
    model.result("pg5").feature("line2").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg5").feature("line2").feature("def1").set("expr", new String[]{"tsf1.dXg", "tsf1.dYg"});
    model.result("pg5").feature("line2").feature("def1")
         .set("descr", "\u8fb9\u754c\u4f4d\u79fb \uff08\u51e0\u4f55\u5750\u6807\u7cfb\uff09");
    model.result("pg5").feature("line2").feature("def1").set("expr", new String[]{"-tsf1.dXg", "-tsf1.dYg"});
    model.result("pg5").feature("line2").feature("def1").set("scaleactive", true);
    model.result("pg5").feature("line2").feature("def1").set("scale", 1);
    model.result("pg5").run();
    model.result("pg5").feature("arwp1").feature("col1").set("expr", "sqrt(material.dX^2+material.dY^2)");
    model.result("pg5").feature("arwp1").feature("col1").set("rangecoloractive", false);
    model.result("pg5").run();
    model.result("pg5").feature("line1").create("filt1", "Filter");
    model.result("pg5").run();
    model.result("pg5").feature("line1").feature("filt1").set("expr", "(abs(X)<1e-6)*(abs(Y)<1e-6)");
    model.result("pg5").run();
    model.result("pg5").feature("arwp1").feature().copy("filt1", "pg5/line1/filt1");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("line2").feature().copy("filt1", "pg5/line1/filt1");
    model.result("pg5").run();
    model.result("pg5").run();

    model.title("\u7528\u4e8e\u591a\u8def\u5206\u89e3\u7684\u5149\u5b50\u6676\u4f53\u7684\u4f18\u5316");

    model
         .description("\u672c\u6a21\u578b\u6a21\u62df\u4e09\u7aef\u53e3\u5149\u5b50\u6676\u4f53\u4e2d\u4e24\u4e2a\u9891\u6bb5\u7684\u4f20\u64ad\u3002\u6676\u4f53\u6700\u521d\u662f\u5bf9\u79f0\u7684\uff0c\u56e0\u6b64\u4e24\u4e2a\u9891\u6bb5\u76f4\u63a5\u5bf9\u5e94\u4e8e\u4efb\u4e00\u8f93\u51fa\u7aef\u53e3\u3002\u8be5\u6a21\u578b\u5bf9\u6676\u67f1\u7684\u4f4d\u7f6e\u8fdb\u884c\u4f18\u5316\uff0c\u4ee5\u4f7f\u4e24\u4e2a\u9891\u6bb5\u5230\u8fbe\u4e0d\u540c\u7684\u8f93\u51fa\u7aef\u53e3\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("photonic_crystal_demultiplexer_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
